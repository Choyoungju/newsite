select  rownum, no, name, message
  from guestbook
where (page-1)*5+1 <= rownum and rownum <= (page*5) + 1; 


select  rownum, no, name, message
  from guestbook
where 6 <= rownum and rownum <= 10; 


select *
 from ( select  rownum as r, no, name, message
            from guestbook)
  where 6 <= r;	
  
select rownum as r, c.*  
  from (   select  no, name, message
                from guestbook
           order by 	 reg_date desc) c
		   
	 		  