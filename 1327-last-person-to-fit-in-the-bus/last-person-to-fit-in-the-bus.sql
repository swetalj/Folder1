-- Write your PostgreSQL query statement below

with new_table as(
    select person_name,sum(weight) over (order by turn) as cumulative_weight from queue
)
select person_name from new_table where cumulative_weight <=1000
order by cumulative_weight desc limit 1;