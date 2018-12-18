package k.chapter.database;

public class EDenormalization {
    /* Question
    What is Denormalization? Explain the pros and cons */
    /*
    Normalization:
    Normalized databases fair very well under conditions where the applications are write-intensive
    and the write-load is more than the read-load.

    Normalization is concept of modularizing the table (i.e. making the table as
    simple as possible by separating the tables if there is no need for them exist
    together).
    e.g. Suppose we are working on Contacts of a person. So we would require the
    Person table where we can put all the details like personID, personFirstName,
    personLastName, mailID, mobileNumber, landlineNumber. Now suppose if the person
    has more than one mailID, lets say two. In this case if we are going to create
    a new column mailIDTwo, then there will be some null values for the person
    who are having only one emailID, and apart from null values what if one another
    person has a few more emailID. We can't go on and on by creating columns.
    Instead we can have a new table only for Email where we can refer to the person
    using personID as FOREIGN KEY. This is just one form of Normalization
    THREE orders are normalization are available. Basically it avoids the duplication
    of the columns and the data, by using the relation (linking using PRIMARY KEY
    FOREIGN KEY concept)

    Denormalization:
    Denormalized databases fair well under heavy read-load and when the application is read intensive.

    Suppose the company a large scable product and it involves a very large number
    of tables and the JOINS will become expensive in this case. Suppose the query is
    created using JOIN each and everytime this will be a overhead. Also when a certain
    column from another column is often called in other table rather than having to each
    time join both the tables, we can denormalize the table by having the duplication of
    the column in the other table.
    Pros: Since we are avoiding JOIN the performance will increase
    Cons: If suppose there is a database Oneshot queries that are needed to be performed
    affecting the duplicated column which we created earlier, then our oneshot script has
    to be affecting all the places.

    Pros and cons of denormalized database design. [This part has been taken from an online article]
    <http://www.ovaistariq.net/199/databases-normalization-or-denormalization-which-is-the-better-technique/>
    Denormalized databases fair well under heavy read-load and when the application is read intensive. This is because
    of the following reasons:

    The data is present in the same table so there is no need for any joins, hence the selects are very fast.
    A single table with all the required data allows much more efficient index usage. If the columns are indexed
    properly, then results can be filtered and sorted by utilizing the same index. While in the case of a normalized
    table, since the data would be spread out in different tables, this would not be possible. Although for reasons
    mentioned above selects can be very fast on denormalized tables, but because the data is duplicated, the updates
    and inserts become complex and costly.

    Having said that neither one of the approach can be entirely neglected, because a real world application is going
    to have both read-loads and write-loads. Hence the correct way would be to utilize both the normalized and
    denormalized approaches depending on situations.
    */
}
