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

SELECT Buildings.BuildingID, Buildings.BuildingName, BuildingsWithOpenRequest.Number
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