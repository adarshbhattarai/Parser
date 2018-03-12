(1) Write MySQL query to find IPs that mode more than a certain number of requests for a given time period.

   Ex: Write SQL to find IPs that made more than 100 requests starting from 2017-01-01.13:00:00 to 2017-01-01.14:00:00.

(2) Write MySQL query to find requests made by a given IP.


1)
select ip from access_logs where startdate
between '2017-01-01 15:00:00' and  '2017-01-01 15:59:59'
group by ip having count(ip)> 200 ;

select ip,count(ip) c from access_logs where startdate between '2017-01-01 13:00:00'
and '2017-01-01 14:00:00' group by ip having c>100;

2 )

select * from access_logs where ip='192.168.169.194';