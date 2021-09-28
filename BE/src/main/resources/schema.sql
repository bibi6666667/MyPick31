drop table if exists ``;

create table `base` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    `is_sherbet` tinyint(1) not null,
    `is_sorbet` tinyint(1) not null,
    `base_category` varchar(45) not null,
    primary key (`id`)
);

create table `topping` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    `is_choco_coated` tinyint(1) not null,
    `topping_category` varchar(45),
    primary key (`id`)
);

create table `syrup` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    `syrup_category` varchar(45),
    primary key (`id`)
);

create table `allergen` (
    `id` int not null auto_increment,
    `allergen_type` varchar(45) not null,
    primary key (`id`)
);

create table `image` (
    `id` int not null auto_increment,
    `has_image` tinyint(1) not null,
    `image_address` varchar(100),
    primary key (`id`)
);

create table `flavor` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    `kcal` int not null,
    `is_signature` tinyint(1) not null,
    `is_discontinued` tinyint(1) not null,
    `info` varchar(100),
    `image_id` int,
    primary key (`id`),
    foreign key (`image_id`) references `image` (`id`)
);