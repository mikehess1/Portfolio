# Rentals

This program uses the `requests` module to make GET requests to the Hotwire API at [developer.hotwire.com(http://developer.hotwire.com)]. More specifically it is using the Rental Car Shopping API found here http://developer.hotwire.com/docs/read/Rental_Car_Shopping_API. To run this program, you will need to register and obtain an API key. **Note: The Hotwire API docs website is fairly dated and is NOT using a secure HTTPS connection. Proceed with caution and do not enter any sensitive information**

For each GET request, the response is parsed and the top 3 cheapest options are stored. As cheaper options are discovered, more expensive options are replaced. When all pages have been parsed, the following will be provided for the top 3 cheapest options:

- Pickup Time
- Direct Link to the rental car details
- Search Link showing all results for the specified pickup time

As of now, the dates and time range are hard coded into the source. Ideally, the user would be able to specify their own input, but this is currently only for personal use.
