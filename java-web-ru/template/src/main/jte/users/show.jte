@import exercise.dto.users.UserPage
@param UserPage page

<html>
    <title>Юзеры</title>
    <body>
        <h1>${page.getUser().getFirstName() + " " +  page.getUser().getLastName()}</h1>
    </body>
</html>