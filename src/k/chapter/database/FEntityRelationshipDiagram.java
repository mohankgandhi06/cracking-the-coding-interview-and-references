package k.chapter.database;

public class FEntityRelationshipDiagram {
    /* Question:
     * Draw an entity-relationship diagram for a database with companies, people and
     * professionals (people who work for companies)*/

    /* COMPANY TABLE
     *   - ID
     *   - Name
     *   - Address*
     *
     *  PERSON TABLE (People)
     *   - ID
     *   - FirstName
     *   - LastName
     *   - Sex*
     *   - DateOfBirth*
     *   - Phone*
     *   - Address*
     *
     *  EMPLOYEE TABLE (Professional)
     *   - ID
     *   - PersonID
     *   - CompanyID
     *   - Degree*
     *   - JobRole
     *   - Salary
     *   - DateOfJoining
     *   - DateOfLeaving
     *
     *   Fields with the star(*) are included after referring to the answer.
     *   Rest are deduced before.*/
}
