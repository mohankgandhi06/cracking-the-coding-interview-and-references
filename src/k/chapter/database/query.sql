create table Apartments (AptID int, UnitNumber varchar(10), BuildingID int);
insert into Apartments (AptID, UnitNumber, BuildingID) values(1,  "1234567890", 1);
insert into Apartments (AptID, UnitNumber, BuildingID) values(2,  "2345678901", 1);
insert into Apartments (AptID, UnitNumber, BuildingID) values(3,  "3456789012", 11);
insert into Apartments (AptID, UnitNumber, BuildingID) values(4,  "4567890123", 11);
insert into Apartments (AptID, UnitNumber, BuildingID) values(5,  "5678901234", 2);
insert into Apartments (AptID, UnitNumber, BuildingID) values(6,  "6789012345", 2);
insert into Apartments (AptID, UnitNumber, BuildingID) values(7,  "7890123456", 11);
insert into Apartments (AptID, UnitNumber, BuildingID) values(8,  "8901234567", 4);
insert into Apartments (AptID, UnitNumber, BuildingID) values(9,  "9012345678", 6);
insert into Apartments (AptID, UnitNumber, BuildingID) values(10, "0123456789", 1);
insert into Apartments (AptID, UnitNumber, BuildingID) values(11, "0123456789", 5);
insert into Apartments (AptID, UnitNumber, BuildingID) values(12, "0123456789", 6);
insert into Apartments (AptID, UnitNumber, BuildingID) values(13, "0123456789", 2);
insert into Apartments (AptID, UnitNumber, BuildingID) values(14, "0123456789", 5);
insert into Apartments (AptID, UnitNumber, BuildingID) values(15, "0123456789", 4);
insert into Apartments (AptID, UnitNumber, BuildingID) values(16, "0123456789", 6);
insert into Apartments (AptID, UnitNumber, BuildingID) values(17, "0123456789", 5);

create table Buildings (BuildingID int, ComplexID int, BuildingName varchar(100), Address varchar(500));
insert into Buildings (BuildingID, ComplexID, BuildingName, Address) values(1,  1, "Athena Palace",        "12, New Portland, North Canada");
insert into Buildings (BuildingID, ComplexID, BuildingName, Address) values(2,  1, "Apollo International", "15, New Portland, North Canada");
insert into Buildings (BuildingID, ComplexID, BuildingName, Address) values(11, 2, "Zeus Hall",            "123/2, Mark Ruffalo Street, Kentucky");
insert into Buildings (BuildingID, ComplexID, BuildingName, Address) values(4,  3, "Arthas Kingdom",       "1, Newfoundland, California");
insert into Buildings (BuildingID, ComplexID, BuildingName, Address) values(5,  2, "Mars Land",            "150/6, Mark Ruffalo Street, Kentucky");
insert into Buildings (BuildingID, ComplexID, BuildingName, Address) values(6,  1, "Mandrake Villa",       "20, New Portland, North Canada");

create table Requests (RequestID int, Status varchar(100), AptID int, Description varchar(500));
insert into Requests (RequestID, Status, AptID, Description) values(1,  "Open",   1, "Available for Renting");
insert into Requests (RequestID, Status, AptID, Description) values(2,  "Closed", 2, "Closed because person is staying");
insert into Requests (RequestID, Status, AptID, Description) values(3,  "Open",   3, "Available for Renting");
insert into Requests (RequestID, Status, AptID, Description) values(4,  "Closed", 4, "Closed because person is staying");
insert into Requests (RequestID, Status, AptID, Description) values(5,  "Open",   5, "Available for Renting");
insert into Requests (RequestID, Status, AptID, Description) values(6,  "Closed", 6, "Closed for Renovation");
insert into Requests (RequestID, Status, AptID, Description) values(7,  "Closed", 7, "Closed since too many issue are Pending");
insert into Requests (RequestID, Status, AptID, Description) values(8,  "Open",   8, "Available for Renting");
insert into Requests (RequestID, Status, AptID, Description) values(9,  "Open",   9, "Available for Renting");
insert into Requests (RequestID, Status, AptID, Description) values(10, "Closed", 10, "Closed for Renovation");

create table Complexes (ComplexID int, ComplexName varchar(100));
insert into Complexes (ComplexID, ComplexName) values(1, "Roman Arena");
insert into Complexes (ComplexID, ComplexName) values(2, "Island Estate");
insert into Complexes (ComplexID, ComplexName) values(3, "Penta Villar");

create table AptTenants (TenantID int, AptID int);
insert into AptTenants (TenantID, AptID) values(4, 1);
insert into AptTenants (TenantID, AptID) values(1, 2);
insert into AptTenants (TenantID, AptID) values(2, 3);
insert into AptTenants (TenantID, AptID) values(4, 5);
insert into AptTenants (TenantID, AptID) values(5, 4);
insert into AptTenants (TenantID, AptID) values(6, 8);
insert into AptTenants (TenantID, AptID) values(7, 6);
insert into AptTenants (TenantID, AptID) values(8, 9);
insert into AptTenants (TenantID, AptID) values(8, 10);
insert into AptTenants (TenantID, AptID) values(9, 7);
insert into AptTenants (TenantID, AptID) values(10, 14);
insert into AptTenants (TenantID, AptID) values(11, 16);
insert into AptTenants (TenantID, AptID) values(12, 15);
insert into AptTenants (TenantID, AptID) values(13, 13);
insert into AptTenants (TenantID, AptID) values(14, 11);
insert into AptTenants (TenantID, AptID) values(15, 12);
insert into AptTenants (TenantID, AptID) values(16, 17);

create table Tenants (TenantID int, TenantName varchar(100));
insert into Tenants (TenantID, TenantName) values(1, "John");
insert into Tenants (TenantID, TenantName) values(2, "Sandy");
insert into Tenants (TenantID, TenantName) values(3, "Jamie");
insert into Tenants (TenantID, TenantName) values(4, "Stark");
insert into Tenants (TenantID, TenantName) values(5, "Rob");
insert into Tenants (TenantID, TenantName) values(6, "Stannis");
insert into Tenants (TenantID, TenantName) values(7, "Leonard");
insert into Tenants (TenantID, TenantName) values(8, "Agnes");
insert into Tenants (TenantID, TenantName) values(9, "Sophie");
insert into Tenants (TenantID, TenantName) values(10, "Uma Truman");
insert into Tenants (TenantID, TenantName) values(11, "Regan");
insert into Tenants (TenantID, TenantName) values(12, "Sanders");
insert into Tenants (TenantID, TenantName) values(13, "Alex");
insert into Tenants (TenantID, TenantName) values(14, "Messi");
insert into Tenants (TenantID, TenantName) values(15, "Ronaldo");
insert into Tenants (TenantID, TenantName) values(16, "Ron Weasly");

-- Multiple Apartments: Write a SQL query to get a list of tenants who
-- are renting more than one apartment
SELECT AptTenants.TenantID, Tenants.TenantName, COUNT(AptTenants.AptID) Count
FROM AptTenants LEFT OUTER JOIN Tenants ON AptTenants.TenantID = Tenants.TenantID
GROUP BY AptTenants.TenantID HAVING COUNT(AptTenants.AptID) > 1;

-- Open Requests: Write a SQL query to get a list of all buildings and
-- the number of open requests (Requests in which status is 'Open')
SELECT Requests.AptID, Requests.Status, Apartments.BuildingID,
COUNT(Apartments.BuildingID) Count FROM Requests INNER JOIN Apartments
ON Requests.AptID = Apartments.AptID WHERE Requests.Status = 'Open'
GROUP BY Apartments.BuildingID;

SELECT Buildings.BuildingID, Buildings.BuildingName, IFNULL(BuildingsWithOpenRequest.Number,0) OpenRequests
FROM Buildings LEFT OUTER JOIN
(SELECT Requests.AptID, Requests.Status, Apartments.BuildingID BuildingID,
COUNT(Apartments.BuildingID) Number FROM Requests INNER JOIN Apartments
ON Requests.AptID = Apartments.AptID WHERE Requests.Status = 'Open'
GROUP BY Apartments.BuildingID) BuildingsWithOpenRequest
ON Buildings.BuildingID = BuildingsWithOpenRequest.BuildingID;

-- Close All Requests: Building #11 is undergoing a major renovation.
-- Implement a query to close all requests from apartments in this building
UPDATE Requests SET Requests.Status = 'Closed' WHERE Requests.RequestID IN
(SELECT Requests.RequestID FROM Requests LEFT OUTER JOIN Apartments
ON Requests.AptID = Apartments.AptID WHERE Apartments.BuildingID = 11
AND Requests.Status = 'Open');