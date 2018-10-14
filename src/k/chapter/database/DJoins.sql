/* Joins */
--  What are the different types of joins? Please explain how they differ and why
-- certain types are better in certain situations

-- INNER JOIN   - Match in both the tables
-- When we need only the matching set of row leaving out the rest
-- e.g. Names that being with Letter A?

-- SELF JOIN    - Joins the table to itself
-- When the query involves matching two columns from the same table
-- e.g. Employee and their Manager (Who are in turn only Employees themselves)

-- OUTER JOIN - LEFT OUTER JOIN   - Joins all of Left to matching Right Entries
--            - RIGHT OUTER JOIN  - Joins all of Right to matching Left Entries
--            - FULL OUTER JOIN   - Returns the  row if atleast one side matches
-- This is leveraged when we need to gather the unmatched rows as well of the Left/Right table
-- e.g. Teachers and their Count of students enrolled in their course. Maybe some
-- teachers might be without students at the time, and so the decision can be made
-- to allocate them to some other course instead. So in this case we require null as well
-- FULL OUTER will be used when there are MULTIPLE WHERE CLAUSE from both the table and
-- there can be null in the other table, and yet we need to show them.

-- CROSS JOIN or CARTESIAN JOIN   - like a Nested FOR LOOP.
--                                  first row is matched with all the other rows
--                                  of the other table
-- e.g. Generating permutations. Suppose we are conducting a football or any other game
-- and we need to match up each player with all the other players.