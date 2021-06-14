# Car-Dealer-Android-application
Android Lab Project


Project Description:
A car dealer is asking us to build an Android application which allows the users to view and and reserve cars online or using a local database, the application should be friendly use and simple. The application should include the following functionalities:
1. Introduction layout (10 points)
This layout has a “connect” button which will connect to a Server using REST to load all cars types in
array-List.
1.1. If connection successful → go-to login and registration section.
1.2. If connection unsuccessful → Show error message and stay on the same layout.
2. Login and Registration Layout (30 point)
This layout should have a “Login” button and a “Sign Up” button.
2.1. In the main page (first page) the customer can enter his e-mail and password that are registered in the database to login into a special menu. This layout must have a check box called “remember me” which will save the email in shared preferences so next time we login we don’t need to type the email. (10 points)

2.2. The Sign-Up button will redirect us to another layout where we will enter our email (must be an email) as a primary key, first name (not less than 3 characters), last name (not less than 3 characters), gender (spinner), password (must not be less than 5 characters and must include at least 1 character, 1 number and one special character), confirm password (the password should be encrypted using a secure Hash Function), country (spinner (not less than 4 countries)), city (not less than 3 cities for each country) and one phone number (must have pre zip code (area code) for example Palestine (00970), Jordan (00962), Syria (00963) …etc. ** this must be changeable according to the country NOT by user). If all the previous conditions are entered correctly then and only then we can register. (20 points)

3. Home layout (sign in as normal Customer) (15 point)
This layout should be a Navigation Drawer Activity which will contain in its main page a summary on
the history (Home) of the car dealer and the navigation bar should have the following functionality:
3.1. Home: which will contain the history of the car dealer (main page).

3.2. Car menu: which will contain all the cars types.

3.3. Your reservations: which will contain all the reserved cars the customer has made.

3.4. Your favorites: which will contain all the cars that are marked as favorite from the customer.

3.5. Special Offers: which will contain the special offers made by the car dealer.

3.6. Profile: which will allow the customer to change his password, name and phone number.

3.7. Call us or Find us: this will redirect the customer to call the car dealer or find on google maps.

3.8. Logout: which will log the customer out from this profile and redirect him to the login page.

3.2. Car menu (15 point):
This menu should include all car types each one should be defined by a name (factory name and type) so as if the customer clicked on a name then the details of the that pressed car appears (this must be
done using fragments). Also, a filter to search on a special type should be implemented to search for a car based on (Price), (model) and (name) (these features would be determined or changed later). Although, beside each type there must be a “add to favorites” button which will add the selected car to favorites and a reserve button which will pop up a reserved menu that has details of the car and a submit button to confirm the order.

3.3. Your reservations (5 points)
This will only include all reserved cars that has been reserved each with the date and time of the reservation.

3.4. Your favorites (5 points)
This will include all the favorite cars that were added by the customer. Also, it will have the functionality for reserving as where in the Car menu.

3.5. Special Offers (5 points)
These will be some special offers that are made by the car dealer and they must have the functionality for reserving and add to favorites as in the Car menu.

3.6. Profile (10 points)
In this layout each customer should view all this personal information and be able to change his first
name, last name, password and phone number. (with all the conditions from the sign-up page).

3.7. Call us or Find us (5 points)
This layout should have three buttons the first will call the car dealer on this phone number
”0599000000” the second will open Google maps to find the car dealer store, the third one will open Gmail
to send an email to the car dealer on his main email CarDealer@cars.com (this should be put in the send to
edit text field automatically)

3.8. Logout: (5 points)
which will log the customer out from this profile and redirect him to the login page.


4. Home layout (sign in as Admin) (15 points)
This layout should be a Navigation Drawer Activity which will contain in its main page the profile of
the Admin and the navigation bar should have the following functionality:

4.1. Delete Customers: delete any customer

4.2. Add Admin: add new admin to the car dealer with the same parameters as in sign up page there must be a static admin.

4.3. View All Reserves: view all reserves date, time and name of the customer of each order that is made.

4.4. Logout: which will log the Admin out from this profile and redirect him to the login page.

The project must be designed using Android packages and must use:
Android Layouts (dynamically and statically). Intents and Notifications (toast messages). SQLite Database.
Animation (frame of tween). Fragments.
Shared Preferences.
REST. Using this link :

(http://www.mocky.io/v2/5bfea5963100006300bb4d9a)


