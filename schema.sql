
create database wallethub
use wallethub;


create table `access_logs`(

`id` bigint unsigned AUTO_INCREMENT,
`startdate` timestamp not null,
`ip` varchar(20) not null,
`request` varchar(50) not null,
`status` int not null,
`useragent` varchar(300) not null,
primary key (`id`)
)


create table `BLOCKED_IP`(

`id` bigint unsigned AUTO_INCREMENT,
`data_dt` timestamp not null,
`ip` varchar(20) not null,
`comment` varchar(500) not null,
primary key (`id`)

)
