package com.bsuir.khomyakova.kursach;

import com.bsuir.khomyakova.kursach.model.AppUser;
import com.bsuir.khomyakova.kursach.model.AppUserRole;
import com.bsuir.khomyakova.kursach.model.Car;
import com.bsuir.khomyakova.kursach.model.Client;
import com.bsuir.khomyakova.kursach.repository.AppUserRepository;
import com.bsuir.khomyakova.kursach.repository.CarsRepository;
import com.bsuir.khomyakova.kursach.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static com.bsuir.khomyakova.kursach.model.AppUserRole.ADMIN;
import static com.bsuir.khomyakova.kursach.model.AppUserRole.USER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AppUserRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AppUserRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarsRepository carsRepository;


    @Test
    public void testUserCreation(){

        AppUser user1=new AppUser();
       // user1.setUserId(Long.valueOf(1));
        user1.setEmail("ass@gmail.com");
        user1.setPassword("555");
        user1.setRole(USER);
        repository.save(user1);

        Client client = new Client();
        // client.setClientId(Long.valueOf(1));
        client.setName("Dddd");
        client.setSurname("2d3");
        client.setUser(user1);
        clientRepository.save(client);


    }


    @Test
    public void testAddingCar(){
        Car car = new Car();
        car.setCarName("Mers2");
        car.setColor("красный");
        car.setPrice(175000);

        Client client = clientRepository.findClientByName("Dddd");

        car.setClient(client);

        carsRepository.save(car);
        assertNotNull(car);
        assertThat(car.getCarName()).isEqualTo("Mers2");
    }


@Test
    public void testFind(){
    String email = "user1@gmail.com";
    AppUser users = repository.findAppUserByEmail(email);
    if(users==null){
        System.out.println("Error");
    }
    else {
        System.out.println(users.getUserId());
        System.out.println("Пользователь найден");
        assertThat(users).isNotNull();
    }
}
    @Test
    public void testString()
    {
        List<AppUser> userList = repository.findAll();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < userList.size(); i++) {
            stringBuilder.append(userList.get(i).getUserId());
            stringBuilder.append(" ");
            stringBuilder.append(userList.get(i).getEmail());
            stringBuilder.append(" ");
            stringBuilder.append(userList.get(i).getPassword());
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

}
