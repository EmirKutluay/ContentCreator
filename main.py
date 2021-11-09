import requests

url = "https://imdb8.p.rapidapi.com/title/get-popular-movies-by-genre"

querystring = {"genre":"/chart/popular/genre/adventure"}

headers = {
    'x-rapidapi-host': "imdb8.p.rapidapi.com",
    'x-rapidapi-key': "2ee9edf7cdmsh9fef533ee484a2fp1b5759jsn15dc37d0d1da"
    }

response = requests.request("GET", url, headers=headers, params=querystring)

list = response.text[1:-1].split(',')

for i in range(10):
    s = list[i]
    movieID = s[8:-2]
    url = "https://imdb8.p.rapidapi.com/title/get-details"
    querystring = {"tconst":movieID}
    response = requests.request("GET", url, headers=headers, params=querystring)
    movieInfo = response.text[1:-1].split(',')
    name = ""
    year = 0
    for info in movieInfo:
        if '"title"' in info:
            name = info[9:-1]
        elif '"year"' in info:
            year = info[7:]
    print(name + " (" + year + ")")
