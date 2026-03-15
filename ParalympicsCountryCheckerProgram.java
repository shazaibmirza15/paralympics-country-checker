import java.util.Scanner;

/**************************************************************
@author     Shazaib Mirza
@SID        250411807
@date       1st December 2025
@version    1

A program that checks if a Paralympic team (country) has legal
cyclists for the relay by checking their disability ratings
**************************************************************/

// Record that holds info based on a country in the Paralympic relay competition
class Country
{
    String name;
    int [] disability_ratings;
    int MAX_POINTS;
}//END class Country


class ParalympicsCountryCheckerProgram
{

    //main method
    //
    public static void main (String [] a)
    {

        Country country = new Country();
        String message;
        final int MAX_POINTS = askPointLimit();
        String country_name = askCountryName();
        country = createCountry(country_name, MAX_POINTS, country);
        int [] ratings = askDisabilityRatings();
        country = setRatings(country, ratings);
        message = countryRecordToString(country, MAX_POINTS);

        System.out.println(message);
        return;

    }//END main


    /*ADT methods:
      countryRecordToString
      setRatings
      createCountry
    */

    //Converts array and total disability ratings to string message that can be printed
    public static String countryRecordToString (Country country, int MAX_POINTS)
    {

        int total_rating = country.disability_ratings[0]
                         + country.disability_ratings[1]
                         + country.disability_ratings[2];

        String legality;
        String message;

        if (total_rating <= MAX_POINTS)
        {
            legality = "legal";
        }
        else
        {
            legality = "NOT legal";
        }

        message = country.name + " has D" + country.disability_ratings[0] + ", D"
                + country.disability_ratings[1] + ", D" + country.disability_ratings[2]
                + " for a " + MAX_POINTS + " point race";

        message = message + " -> " + legality;

        return message;

    }//END countryRecordToString


    //Sets the disability ratings for a country
    public static Country setRatings(Country c, int [] ratings)
    {

        c.disability_ratings = ratings;
        return c;

    }//END setRatings


    //Creates a country for the relay competition
    public static Country createCountry (String name, int MAX_POINTS, Country new_country)
    {

        new_country.name = name;
        new_country.MAX_POINTS = MAX_POINTS;

        new_country.disability_ratings = new int [] {MAX_POINTS, MAX_POINTS, MAX_POINTS};

        return new_country;

    }//END createCountry


    //Checks if the rating the user has inputted is valid
    public static boolean ratingIsValid(int r)
    {

        return r >= 1 && r <= 5;

    }//END ratingIsValid


    //Asks disability rating of one cyclist until their input is valid
    public static int askRating (int cyclist_number)
    {

        int rating = askInt("What is the disability rating of cyclist " + cyclist_number + "?");

        while (!ratingIsValid(rating))
        {
            System.out.println("That is not a valid input. Enter a value >=1 & <=5");
            rating = askInt("What is the disability rating of cyclist " + cyclist_number + "?");
        }

        return rating;

    }//END askRating


    //Asks the disability rating of each cyclist
    public static int[] askDisabilityRatings ()
    {

        final int NUMBER_OF_RATINGS = 3;
        int [] ratings = new int[NUMBER_OF_RATINGS];

        for(int i = 0; i < NUMBER_OF_RATINGS; i++)
        {
            ratings[i] = askRating(i + 1);
        }

        return ratings;

    }//END askDisabilityRatings


    //Checks if country's name is valid input
    public static boolean validName (String nm)
    {

        return !nm.equals("");

    }//END validName


    //Asks user what the name of the country is and returns when user enters valid input
    public static String askCountryName ()
    {

        String name = askString("What is the name of the country?");

        while(!validName(name))
        {
            System.out.println("That is not a valid input. Enter a name.");
            name = askString("What is the name of the country?");
        }

        return name;

    }//END askCountryName


    //Checks if the point limit is in the valid range
    public static boolean limitsValid (int pl)
    {

        return pl >= 3;

    }//END limitsValid


    //Asks the point limit for the relay competition
    public static int askPointLimit ()
    {

        int point_limit = askInt("What is the point limit for the relay?");

        while(!limitsValid(point_limit))
        {
            System.out.println("That is not a valid input. Enter a value >=3");
            point_limit = askInt("What is the point limit for the relay?");
        }

        return point_limit;

    }//END askPointLimit


    //Asks user question, returns answer as int
    public static int askInt (String question)
    {

        String answer = askString(question);
        return Integer.parseInt(answer);

    }//END askInt


    //Asks user question, returns answer as String
    public static String askString (String question)
    {

        Scanner s = new Scanner(System.in);
        String answer;

        System.out.println(question);
        answer = s.nextLine();

        return answer;

    }//END askString
}//END class ParalympicsCountryCheckerProgram