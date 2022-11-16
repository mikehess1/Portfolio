#!/bin/python3

import requests
from time import sleep


api_key = '<your_API_KEY_here>'


def getHour(hour):
    if hour == 0:
        return '00'
    if hour < 10:
        return '0' + str(hour)
    return str(hour)


# format and return api_url
def getAPI_URL(hour):
    api_url = "http://api.hotwire.com/v1/search/car?apikey=[key]&format=json&dest=MIA&dropOffDest=MCO&startdate=11/20/2022&enddate=11/21/2022&pickuptime=[hour]:00&dropofftime=[hour]:00"
    return api_url.replace('[key]', api_key).replace('[hour]', str(hour))


# format and return search link
def getSearchLink(hour):
    search_link = "https://www.hotwire.com/car-rentals/search?origin=MIA&destination=MCO&start=2022-11-20T[hour]:00:00&end=2022-11-21T[hour]:00:00"
    return search_link.replace('[hour]', str(hour))


# get api_url and call GET request, return JSON
def getJSON(hour):
    api_url = getAPI_URL(getHour(hour))
    return requests.get(api_url).json()


if __name__ == '__main__':
    # declare price list, first and last hour (in 24 hour format)
    prices = []
    first_hour = 8
    last_hour = 9

    # for each hour, first to last, request API data and parse
    for hour in range(first_hour, last_hour + 1):

        # make API get request and store response
        response = getJSON(hour)

        # for each result in response, check for cheaper result and update if found
        for item in response['Result']:
            # store price of current result
            price = float(item['TotalPrice'])

            # add first 3 results to prices list and sort
            if len(prices) < 3:
                prices.append((price, item))
                prices.sort(key=lambda p: p[0])
            else:
                # if cheaper option is found, add and remove most expensive
                for n in range(len(prices[0])):
                    if price < prices[n][0]:
                        prices.insert(n, (price, item))
                        prices.pop()

        # limit maximum of 5 requests per second
        sleep(.2)

    # print results
    print()
    for p in prices:
        print('$'+'%.2f' % p[0])
        print("Pickup:", p[1]['PickupTime'])
        print("Direct Link:\n", p[1]['DeepLink'])
        # TODO make sure this uses the correct hour HH
        print('Search Link:\n' + getSearchLink(p[1]['PickupTime'][0:2]), '\n')
