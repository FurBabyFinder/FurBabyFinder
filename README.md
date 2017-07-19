# FURBABYFINDER README

Instructions and information about using FurBabyFinder for your animal rescue organization

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Support](#support)
- [Contributing](#contributing)

## Installation

You will need to set up a server environment that includes Mavin, Spring and MySql:
Fork the project into your own repository and pull it into your project.
Set up your own applications properties:
    spring.datasource.url=your database
    spring.datasource.username=your username
    spring.datasource.password=your password
    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=update
Add your own Google Forms for foster, adoption and play-date forms and then insert the form links into the related files  
Update the styling with the Logo, Titles and colors for your orgaization
  (additional instructions pending as project is developed)



## Usage

This application is for use by animal rescues to have low cost and robust software 
to track their foster animals and allow potential adopters to connect and find their fur babies.

## Support

Add and issue at https://github.com/FurBabyFinder/FurBabyFinder/issues


## Contributing

Please contribute using [Github Flow](https://guides.github.com/introduction/flow/). Create a branch, add commits, and [open a pull request](https://github.com/FurBabyFinder/FurBabyFinder/pulls).