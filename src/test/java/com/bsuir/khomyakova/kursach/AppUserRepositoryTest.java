package com.bsuir.khomyakova.kursach;

import com.bsuir.khomyakova.kursach.model.*;
import com.bsuir.khomyakova.kursach.repository.AppUserRepository;
import com.bsuir.khomyakova.kursach.repository.CarsRepository;
import com.bsuir.khomyakova.kursach.repository.ClientRepository;
import com.bsuir.khomyakova.kursach.repository.OrderRepository;
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

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void startPage() {
        List<Car> list1 = carsRepository.findAll();
        System.out.println(list1.get(0).getCarId());

    }
    @Test
    public void testUserCreation(){

        AppUser user1=new AppUser();
       // user1.setUserId(Long.valueOf(1));
        user1.setEmail("admin@gmail.com");
        user1.setPassword("111");
        user1.setRole(ADMIN);
        repository.save(user1);

//        Client client = new Client();
//        // client.setClientId(Long.valueOf(1));
//        client.setName("Анна");
//        client.setSurname("Хомякова");
//        client.setUser(user1);
//        clientRepository.save(client);


    }


    @Test
    public void testAddingCar(){
        Car car = new Car();
        car.setCarId(Long.valueOf(1));
        car.setCarName("Audi");
        car.setColor("черный");
        car.setPrice(8000);

        carsRepository.save(car);

        AppUser user1=new AppUser();
        user1.setUserId(Long.valueOf(1));
        user1.setEmail("ann@gmail.com");
        user1.setPassword("888");
        user1.setRole(USER);
        repository.save(user1);

        Client client = new Client();
        client.setClientId(Long.valueOf(1));
        client.setName("Анна");
        client.setSurname("Хомякова");
        client.setUser(user1);
        clientRepository.save(client);

        Order order = new Order();
        order.setOrderId(Long.valueOf(1));
        order.setCar(car);
        order.setClient(client);

        orderRepository.save(order);

//       Car  car =  carsRepository.findCarByName("Audi");
//         List<Car> cars =carsRepository.findAll();
//         System.out.println(cars);
//         //cars.get(0);
//        Client client = clientRepository.findClientByName("Dddd");
//        //client.setCars(cars);
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

    @Test
    public void newOrder() {
        Order order = new Order();

        Client client = clientRepository.findClientById(Long.valueOf(1));
        Car car = carsRepository.findById(Long.valueOf(1)).get();

        order.setOrderId(Long.valueOf(1));
        order.setCar(car);
        order.setClient(client);

        orderRepository.save(order);

    }

}
