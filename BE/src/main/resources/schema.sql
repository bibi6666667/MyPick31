drop table if exists `base_type`;
create table `base_type` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    primary key (`id`)
);

drop table if exists `topping_type`;
create table `topping_type` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    primary key (`id`)
);

drop table if exists `syrup_type`;
create table `syrup_type` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    primary key (`id`)
);

-------------

drop table if exists `base`;
create table `base` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    `is_sherbet` tinyint(1) not null,
    `is_sorbet` tinyint(1) not null,
    `base_type_id` int not null,
    primary key (`id`),
    foreign key (`base_type_id`) references `base_type` (`id`)
);

drop table if exists `topping`;
create table `topping` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    `topping_type_id` varchar(45),
    primary key (`id`),
    foreign key (`topping_type_id`) references `topping_type` (`id`)
);

drop table if exists `syrup`;
create table `syrup` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    `syrup_type_id` varchar(45),
    primary key (`id`),
    foreign key (`syrup_type_id`) references `syrup_type` (`id`)
);

drop table if exists `allergen`;
create table `allergen` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    primary key (`id`)
);

drop table if exists `info`;
create table `info` (
    `id` int not null auto_increment,
    `flavor_id` int not null,
    `content` varchar(100) not null,
    primary key(`id`),
    foreign key (`flavor_id`) references `flavor` (`id`);
);

drop table if exists `image`;
create table `image` (
    `id` int not null auto_increment,
    `flavor_id` int not null,
    `image_address` varchar(100) not null,
    primary key (`id`),
    foreign key (`flavor_id`) references `flavor` (`id`);
);

drop table if exists `flavor`;
create table `flavor` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    `kcal` int not null,
    `is_signature` tinyint(1) not null,
    `is_discontinued` tinyint(1) not null,
    primary key (`id`)
);

------ N:M Tables

drop table if exists `flavor_base`;
create table `flavor_base` (
    `id` int not null auto_increment,
    `flavor_id` int not null,
    `base_id` int not null,
    primary key (`id`),
    foreign key (`flavor_id`) references `flavor` (`id`),
    foreign key (`base_id`) references `base` (`id`)
);

drop table if exists `flavor_topping`;
create table `flavor_topping` (
    `id` int not null auto_increment,
    `flavor_id` int not null,
    `topping_id` int not null,
    primary key (`id`),
    foreign key (`flavor_id`) references `flavor` (`id`),
    foreign key (`topping_id`) references `topping` (`id`)
);

drop table if exists `flavor_syrup`;
create table `flavor_syrup` (
    `id` int not null auto_increment,
    `flavor_id` int not null,
    `syrup_id` int not null,
    primary key (`id`),
    foreign key (`flavor_id`) references `flavor` (`id`),
    foreign key (`syrup_id`) references `syrup` (`id`)
);

drop table if exists `flavor_allergen`;
create table `flavor_allergen` (
    `id` int not null auto_increment,
    `flavor_id` int not null,
    `allergen_id` int not null,
    primary key (`id`),
    foreign key (`flavor_id`) references `flavor` (`id`),
    foreign key (`allergen_id`) references `allergen` (`id`)
);