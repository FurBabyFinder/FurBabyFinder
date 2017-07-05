INSERT INTO users (username, email, password, first_name, last_name, street_addr_1, street_addr_2, city, state, zip, main_phone, alt_phone, foster_app_info, adopt_app_info, id)
VALUES
  ("frenchfryes", "dave@mail.com", "codeup", "Bill", "Smith", "13249 Washita Way", "", "San Antonio", "Texas", "47401",
                  "2104947321", "", "", "", 1),
  ("wannapup", "tom@mail.com", "codeup", "Bob", "Skylar", "13242 Apache Way", "", "San Antonio", "Texas", "47403",
               "2104437321", "", "", "", 2),
("sarahisdabomb", "sarah@mail.com", "codeup", "Sarah", "Heisenberg", "132 Blue Way", "", "San Antonio", "Texas", "47405",
                                                                                     "2106637321", "", "", "", 3),
("mellibelly", "melanie@mail.com", "codeup", "Melanie", "Tucon", "43249 Yahoo Way", "", "San Antonio", "Texas", "47401",
                                                                                 "2914947321", "", "", "", 4);

INSERT INTO pets (ID, name, story, age, private_notes, ready_to_adopt, species)
VALUES (1, "Bentley", "In west Philadelphia born and raised On the playground was where I spent most of my days. ", 3, "Just an all around awesome pup", TRUE, "Dog"),
  (2, "Tiny", "He was found by a priest abandoned and chained to a pole in the backyard", 2, "He snorts like a pig", TRUE, "Dog"),
  (3, "Major", "Found in an abandoned home. He has a big heart and a big smile.", 2, "He likes tennis balls", TRUE,"Dog"),
  (4, "Susie", "She was found in a box outside our office", 1, "She likes to pet you back", FALSE, "Cat"),
  (5, "Henry", "He was found at the ege of a cliff.", 4, "He likes to race against horses", FALSE, "Donkey"),
  (6, "Ms. Cuddlesworth", "She was found in an alley in a box outside of McDonals", 2, "She loves to cuddle", TRUE, "Cat"),
  (7, "Dempsey", "He was won at a biker rally but was not wanted.", 1, "He will verbally communicate", TRUE , "Dog"),
  (8, "TimmyD", "He is a retired circus animal.", 12, "He can jump thru a flaming hula hoop.", FALSE, "Tiger"),
  (9, "Barney", "Former TV star turned homeless looking for his furrever home.", 10, "He loves you", FALSE, "Dinosaur"),
  (10, "Tommy Boy", "Considered the black sheep of his family he was ostracized.", 2, "He means well", FALSE, "Sheep");