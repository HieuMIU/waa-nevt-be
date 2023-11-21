package nevt.config;

import nevt.common.constants.Role;
import nevt.common.extensions.GuidGenerator;
import nevt.dto.car.AttributeItemDTO;
import nevt.dto.car.AttributeTypeDTO;
import nevt.dto.car.CarDTO;
import nevt.models.account.User;
import nevt.repositories.account.UserRepository;
import nevt.repositories.car.CarRepository;
import nevt.services.CarService;
import nevt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SeedDataConfig implements CommandLineRunner {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //Add account
        if (userRepository.count() == 0) {
            User adminUser = new User("Thang", "Nguyen", "admin@gmail.com", passwordEncoder.encode("616940"), Role.ROLE_ADMIN);
            userService.add(adminUser);

            User employeeUser = new User("Hieu", "Tran", "emp@gmail.com", passwordEncoder.encode("616944"), Role.ROLE_EMPLOYEE);
            userService.add(employeeUser);

            User user = new User("Dinh Thang", "Nguyen", "dnguyen@miu.edu", passwordEncoder.encode("616940"), Role.ROLE_USER);
            userService.add(user);
            user = new User("Giao Hieu", "Tran", "gtran@miu.edu", passwordEncoder.encode("616944"), Role.ROLE_USER);
            userService.add(user);
        }

        if (carRepository.count() == 0) {

            //Model S
            Collection<AttributeTypeDTO> types = new ArrayList<>();
            List<AttributeItemDTO> items = new ArrayList<>();
            items.add(new AttributeItemDTO("Pearl", 0));
            items.add(new AttributeItemDTO("Solid Black", 550));
            items.add(new AttributeItemDTO("Deep Blue Metallic", 150));
            items.add(new AttributeItemDTO("Stealth Grey", 225));
            items.add(new AttributeItemDTO("Ultra Red", 475));

            List<AttributeItemDTO> items2 = new ArrayList<>();
            items2.add(new AttributeItemDTO("19 Tempest wheels", 0));
            items2.add(new AttributeItemDTO("21 Tempest wheels", 4500));

            List<AttributeItemDTO> items3 = new ArrayList<>();
            items3.add(new AttributeItemDTO("All Black", 0));
            items3.add(new AttributeItemDTO("Black and White", 2000));
            items3.add(new AttributeItemDTO("Cream", 2000));

            List<AttributeItemDTO> items4 = new ArrayList<>();
            items4.add(new AttributeItemDTO("Steering Wheel", 0));
            items4.add(new AttributeItemDTO("Yoke Steering", 1000));

            List<AttributeItemDTO> items5 = new ArrayList<>();
            items5.add(new AttributeItemDTO("No", 0));
            items5.add(new AttributeItemDTO("Yes", 6000));

            types.add(new AttributeTypeDTO("Paint",items));
            types.add(new AttributeTypeDTO("Wheels", items2));
            types.add(new AttributeTypeDTO("Interior", items3));
            types.add(new AttributeTypeDTO("Steering Control", items4));
            types.add(new AttributeTypeDTO("Enhanced Autopilot", items5));

            Collection<String> images = new ArrayList<>();
            images.add("100000-1.jpg");
            images.add("100000-2.jpg");
            images.add("100000-3.jpg");
            images.add("100000-4.jpg");
            images.add("100000-5.jpg");

            CarDTO carDTO = new CarDTO(GuidGenerator.generateGuid(),
                    "Tesla Model S",
                    74990D,
                    "The car that kickstarted the EV revolution is starting to show its age. While the 2023 Tesla Model S " +
                                "falls behind newly developed rivals with more over-the-top features and longer driving ranges, " +
                            "the sedan-like hatchback continues to be competitive. After all, the Model S offers up to 405 miles of " +
                            "range on a single charge. Plus, the 1020-hp tri-motor Plaid performance model leaps to 60 mph in just 2.1 seconds," +
                            " which is as quick as a multimillion-dollar hypercar. However, cutting-edge competitors such as the BMW i7 and Lucid Air h" +
                            "ave taken much of the Tesla's spotlight. The Model S also has a six-figure price tag that just doesn't jibe with its mid-luxe interior, " +
                            "especially compared with the Lucid and BMW's deluxe cabins. We think the Tesla's odd yoke-style steering wheel compromises the driving experience, " +
                            "too, but at least a regular wheel is once again available. Still, the 2023 Model S remains the brand's flagship, offering access to " +
                            "its most up-to-date software and Tesla's advanced—albeit somewhat dubious—AutoPilot hands-free driving mode.",
                    2012,
                    "Model S",
                    "Tesla",
                    5,
                    types,
                    images);
            carService.add(carDTO);

            //Model 3
            types = new ArrayList<>();
            items = new ArrayList<>();
            items.add(new AttributeItemDTO("Midnight", 0));
            items.add(new AttributeItemDTO("Solid Black", 1000));
            items.add(new AttributeItemDTO("Deep Blue Metallic", 1000));
            items.add(new AttributeItemDTO("Stealth Grey", 1500));
            items.add(new AttributeItemDTO("Ultra Red", 2000));

            items2 = new ArrayList<>();
            items2.add(new AttributeItemDTO("18 Aero wheels", 0));
            items2.add(new AttributeItemDTO("19 Sport wheels", 1500));

            items3 = new ArrayList<>();
            items3.add(new AttributeItemDTO("All Black", 0));
            items3.add(new AttributeItemDTO("Black and White", 1000));
            items3.add(new AttributeItemDTO("Cream", 2000));

            items4 = new ArrayList<>();
            items4.add(new AttributeItemDTO("Steering Wheel", 0));
            items4.add(new AttributeItemDTO("Yoke Steering", 1000));

            items5 = new ArrayList<>();
            items5.add(new AttributeItemDTO("No", 0));
            items5.add(new AttributeItemDTO("Yes", 6000));

            types.add(new AttributeTypeDTO("Paint",items));
            types.add(new AttributeTypeDTO("Wheels", items2));
            types.add(new AttributeTypeDTO("Interior", items3));
            types.add(new AttributeTypeDTO("Steering Control", items4));
            types.add(new AttributeTypeDTO("Enhanced Autopilot", items5));

            images = new ArrayList<>();
            images.add("100001-1.jpg");
            images.add("100001-2.jpg");
            images.add("100001-3.jpg");
            images.add("100001-4.jpg");
            images.add("100001-5.jpg");
            images.add("100001-5.jpg");
            carDTO = new CarDTO("b8f29c31-6f79-4cc4-b939-f5048d4a4f3c",
                    "Tesla Model 3",
                    28000D,
                    "The Tesla Model 3 is a battery electric compact executive car with a fastback body style built by Tesla, Inc. since 2017. " +
                            "The Model 3 was marketed as being more affordable to more people than previous models by Tesla. " +
                            "The Model 3 was the world's top selling plug-in electric car for three years, from 2018 to 2020, " +
                            "before being knocked from the top spot by the Tesla Model Y, a crossover SUV based on the Model 3 chassis. " +
                            "In June 2021, the Model 3 became the first electric car to pass global sales of 1 million. A refresh of the Tesla Model 3 was introduced in 2023, offering a revised exterior and interior.",
                    2018,
                    "Model 3",
                    "Tesla",
                    7,
                    types,
                    images);
            carService.add(carDTO);

            //Model X
            types = new ArrayList<>();
            items = new ArrayList<>();
            items.add(new AttributeItemDTO("Midnight", 0));
            items.add(new AttributeItemDTO("Solid Black", 0));
            items.add(new AttributeItemDTO("Deep Blue Metallic", 0));
            items.add(new AttributeItemDTO("Stealth Grey", 0));
            items.add(new AttributeItemDTO("Ultra Red", 0));

            items2 = new ArrayList<>();
            items2.add(new AttributeItemDTO("20 Cyberstream wheels", 0));
            items2.add(new AttributeItemDTO("22 Turbine wheels", 5500));

            items3 = new ArrayList<>();
            items3.add(new AttributeItemDTO("All Black", 0));
            items3.add(new AttributeItemDTO("Black and White", 2000));
            items3.add(new AttributeItemDTO("Cream", 2000));

            items4 = new ArrayList<>();
            items4.add(new AttributeItemDTO("Steering Wheel", 0));
            items4.add(new AttributeItemDTO("Yoke Steering", 1000));

            items5 = new ArrayList<>();
            items5.add(new AttributeItemDTO("No", 0));
            items5.add(new AttributeItemDTO("Yes", 6000));

            Collection<AttributeItemDTO> items6 = new ArrayList<>();
            items6.add(new AttributeItemDTO("5", 0));
            items6.add(new AttributeItemDTO("6", 6500));
            items6.add(new AttributeItemDTO("7", 3500));

            types.add(new AttributeTypeDTO("Paint",items));
            types.add(new AttributeTypeDTO("Wheels", items2));
            types.add(new AttributeTypeDTO("Interior", items3));
            types.add(new AttributeTypeDTO("Steering Control", items4));
            types.add(new AttributeTypeDTO("Enhanced Autopilot", items5));

            types.add(new AttributeTypeDTO("Seating Layout", items6));

            images = new ArrayList<>();
            images.add("100002-1.jpg");
            images.add("100002-2.jpg");
            images.add("100002-3.jpg");
            images.add("100002-4.jpg");
            images.add("100002-5.jpg");
            carDTO = new CarDTO("878f1dda-08f1-4477-9f32-873b59b1a45f",
                    "Tesla Model X",
                    79990D,
                    "The Tesla Model X is a battery electric mid-size luxury crossover SUV built by Tesla, Inc. since 2015. Developed from the full-sized sedan platform of the Tesla Model S, the vehicle notably uses falcon wing doors for passenger access.\n" +
                            "\n" +
                            "The Model X has an EPA size class as an SUV,[4] and shares around 30 percent of its content with the Model S, half of the originally planned 60 percent, and weighs about 10 percent more. Both the Model X and Model S are produced at the Tesla Factory in Fremont, California. The prototype was unveiled at Tesla's design studios in Hawthorne, California on February 9, 2012.[8][9] First deliveries of the Model X began in September 2015.[10] After one full year on the market, in 2016, the Model X ranked seventh among the world's best-selling plug-in cars.[11] A refresh of the Tesla Model X was introduced in 2021, offering a new \"Plaid\" performance model, along with a revised interior, powertrain, and suspension.[12]\n" +
                            "\n" +
                            "As of September 2023, the Model X is available as a Long-Range version with an estimated EPA range of 348 miles (560 km) and a high performance \"Plaid\" version with an estimated EPA range of 333 miles (536 km).",
                    2015,
                    "Model X",
                    "Tesla",
                    3,
                    types,
                    images);
            carService.add(carDTO);

            //Model Y
            types = new ArrayList<>();
            items = new ArrayList<>();
            items.add(new AttributeItemDTO("Midnight", 1000));
            items.add(new AttributeItemDTO("Solid Black", 1000));
            items.add(new AttributeItemDTO("Deep Blue Metallic", 1000));
            items.add(new AttributeItemDTO("Stealth Grey", 2000));
            items.add(new AttributeItemDTO("Ultra Red", 2000));

            items2 = new ArrayList<>();
            items2.add(new AttributeItemDTO("19 Gemini wheels", 0));
            items2.add(new AttributeItemDTO("20 Induction wheels", 2000));

            items3 = new ArrayList<>();
            items3.add(new AttributeItemDTO("All Black", 0));
            items3.add(new AttributeItemDTO("Black and White", 2000));
            items3.add(new AttributeItemDTO("Cream", 2000));

            items4 = new ArrayList<>();
            items4.add(new AttributeItemDTO("Steering Wheel", 0));
            items4.add(new AttributeItemDTO("Yoke Steering", 1000));

            items5 = new ArrayList<>();
            items5.add(new AttributeItemDTO("No", 0));
            items5.add(new AttributeItemDTO("Yes", 6000));

            items6 = new ArrayList<>();
            items6.add(new AttributeItemDTO("5", 0));

            types.add(new AttributeTypeDTO("Paint",items));
            types.add(new AttributeTypeDTO("Wheels", items2));
            types.add(new AttributeTypeDTO("Interior", items3));
            types.add(new AttributeTypeDTO("Steering Control", items4));
            types.add(new AttributeTypeDTO("Enhanced Autopilot", items5));

            types.add(new AttributeTypeDTO("Seating Layout", items6));

            images = new ArrayList<>();
            images.add("100003-1.jpg");
            images.add("100003-2.jpg");
            images.add("100003-3.jpg");
            images.add("100003-4.jpg");
            images.add("100003-5.jpg");
            carDTO = new CarDTO("8898b120-f4db-4c9d-8779-69bf880ea1ea",
                    "Tesla Model Y",
                    32890D,
                    "The Tesla Model Y is a battery electric mid-size crossover SUV built by Tesla, Inc. since 2020.\n" +
                            "\n" +
                            "The Model Y is based on the Model 3 sedan platform.[4] It shares an estimated 75 percent of its parts with the Tesla Model 3,[5] which includes a similar interior and exterior design and electric powertrain. The Model Y fills a smaller and less expensive segment than the mid-sized Tesla Model X.[6] Like the Model X, the Model Y offers optional third-row seats for a seven-passenger seating capacity.[7][8][9]\n" +
                            "\n" +
                            "Unveiled in March 2019,[10] it started production at its Fremont Factory in January 2020,[11] and started deliveries on March 13, 2020.[12]\n" +
                            "\n" +
                            "In the first and second quarters of 2023, the Model Y outsold the Toyota Corolla to become the world's best-selling car, the first ever electric vehicle to claim the title",
                    2015,
                    "Model Y",
                    "Tesla",
                    3,
                    types,
                    images);
            carService.add(carDTO);


            //2024 Toyota Crown
            types = new ArrayList<>();
            items = new ArrayList<>();
            items.add(new AttributeItemDTO("2.5-liter 4-Cylinder Hybrid", 0));

            items2 = new ArrayList<>();
            items2.add(new AttributeItemDTO("19 Gemini wheels", 0));
            items2.add(new AttributeItemDTO("20 Induction wheels", 2000));

            items3 = new ArrayList<>();
            items3.add(new AttributeItemDTO("Black", 0));
            items3.add(new AttributeItemDTO("Magnetic Gray Metallic", 0));
            items3.add(new AttributeItemDTO("Oxygen White", 425));
            items3.add(new AttributeItemDTO("Heavy Metal", 425));
            items3.add(new AttributeItemDTO("Supersonic Red", 425));

            items4 = new ArrayList<>();
            items4.add(new AttributeItemDTO("Black Fabric", 0));

            items5 = new ArrayList<>();
            items5.add(new AttributeItemDTO("No", 0));
            items5.add(new AttributeItemDTO("Yes", 309));


            types.add(new AttributeTypeDTO("Powertrain",items));
            types.add(new AttributeTypeDTO("Steering Control", items2));
            types.add(new AttributeTypeDTO("Exterior Colors", items3));
            types.add(new AttributeTypeDTO("Interior Colors", items4));
            types.add(new AttributeTypeDTO("All-Weather Floor Liner Package", items5));

            images = new ArrayList<>();
            images.add("100004-1.jpg");
            images.add("100004-2.jpg");
            images.add("100004-3.jpg");
            images.add("100004-4.jpg");
            images.add("100004-5.jpg");
            carDTO = new CarDTO("4d4c16ce-b29b-47b6-a978-aae2f0628957",
                    "2024 Toyota Crown",
                    40050D,
                    "The Toyota Crown (Japanese: トヨタ・クラウン, Hepburn: Toyota Kuraun) is an automobile which has been produced by Toyota in Japan since 1955. It is primarily a line of mid-size luxury cars that is marketed as an upmarket offering in the Toyota lineup.",
                    2024,
                    "Crown",
                    "Toyota",
                    7,
                    types,
                    images);
            carService.add(carDTO);

            //2024 Prius Prime
            types = new ArrayList<>();
            items = new ArrayList<>();
            items.add(new AttributeItemDTO("2.0-liter 4-Cyl.Plug-in Hybrid", 0));

            items2 = new ArrayList<>();
            items2.add(new AttributeItemDTO("19 Gemini wheels", 0));
            items2.add(new AttributeItemDTO("20 Induction wheels", 2000));

            items3 = new ArrayList<>();
            items3.add(new AttributeItemDTO("Black", 0));
            items3.add(new AttributeItemDTO("Magnetic Gray Metallic", 425));
            items3.add(new AttributeItemDTO("Oxygen White", 0));
            items3.add(new AttributeItemDTO("Heavy Metal", 425));
            items3.add(new AttributeItemDTO("Supersonic Red", 425));

            items4 = new ArrayList<>();
            items4.add(new AttributeItemDTO("Black Fabric", 0));

            items5 = new ArrayList<>();
            items5.add(new AttributeItemDTO("No", 0));
            items5.add(new AttributeItemDTO("Yes", 35));


            types.add(new AttributeTypeDTO("Powertrain",items));
            types.add(new AttributeTypeDTO("Steering Control", items2));
            types.add(new AttributeTypeDTO("Exterior Colors", items3));
            types.add(new AttributeTypeDTO("Interior Colors", items4));
            types.add(new AttributeTypeDTO("Front and Rear Parking Assist Package", items5));

            images = new ArrayList<>();
            images.add("100005-1.jpg");
            images.add("100005-2.jpg");
            images.add("100005-3.jpg");
            images.add("100005-4.jpg");
            images.add("100005-5.jpg");
            carDTO = new CarDTO("7d5c611a-6121-442b-a179-00984737bcdd",
                    "2024 Toyota Prius Prime",
                    32675D,
                    "Jaws dropped when Toyota pulled the covers off of the Prius Prime plug-in hybrid and its standard Prius sibling at the start of the 2023 model year. The all-new Prius' radically streamlined design transformed it from one of the most awkward-looking cars on the market to a certified head-turner. Not only did the Prius get a complete makeover, but the Prius Prime plug-in hybrid (PHEV) also features a massively upgraded powertrain. Its gas four-cylinder engine and two electric motors now combine for 220 horsepower, up by 99 horses over the previous Prime. Despite being noticeably quicker than its predecessor, the 2024 Prius Prime also extracts 19 more miles of electric-only driving range from its battery. With the latest Prime, Toyota allowed the Prius to focus on more than just efficiency, turning the plug-in hybrid into a stylish statement with respectable performance.",
                    2024,
                    "Prius Prime",
                    "Toyota",
                    2,
                    types,
                    images);
            carService.add(carDTO);

            //2024 GR Corolla

            types = new ArrayList<>();
            items = new ArrayList<>();
            items.add(new AttributeItemDTO("1.6-liter All-Wheel", 0));

            items2 = new ArrayList<>();
            items2.add(new AttributeItemDTO("18 Aero wheels", 0));
            items2.add(new AttributeItemDTO("19 Sport wheels", 1500));

            items3 = new ArrayList<>();
            items3.add(new AttributeItemDTO("Black", 0));
            items3.add(new AttributeItemDTO("Oxygen White", 0));
            items3.add(new AttributeItemDTO("Supersonic Red", 425));

            items4 = new ArrayList<>();
            items4.add(new AttributeItemDTO("Black Fabric", 0));

            items5 = new ArrayList<>();
            items5.add(new AttributeItemDTO("No", 0));
            items5.add(new AttributeItemDTO("Yes", 1180));


            types.add(new AttributeTypeDTO("Powertrain",items));
            types.add(new AttributeTypeDTO("Steering Control", items2));
            types.add(new AttributeTypeDTO("Exterior Colors", items3));
            types.add(new AttributeTypeDTO("Interior Colors", items4));
            types.add(new AttributeTypeDTO("Performance Package", items5));

            images = new ArrayList<>();
            images.add("100006-1.jpg");
            images.add("100006-2.jpg");
            images.add("100006-3.jpg");
            images.add("100006-4.jpg");
            images.add("100006-5.jpg");
            carDTO = new CarDTO("37b8734b-8e40-4bf7-bc60-2cbcfa844d89",
                    "2024 Toyota GR Corolla",
                    36100D,
                    "The Toyota GR Corolla keeps bringing the heat with the return of the Circuit Edition for model year 2024. This time it adds a Blue Flame color choice for a head turning look, with Ice Cap rounding out the available choices. Black forged aluminum 18-inch BBS ® wheels come standard, similar to the lighter weight wheels equipped on the 2023 MORIZO Edition. Completing the package on the Blue Flame Circuit Edition are black side rocker graphics that run up to the stamped GR-Four logo.",
                    2024,
                    "GR Corolla",
                    "Toyota",
                    5,
                    types,
                    images);
            carService.add(carDTO);
        }
    }

}

