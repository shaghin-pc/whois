import http.client

def main(domain):
    PORT=80
    URL=["/index.html","/admin.php","/secure.php"]
    p=[]
    if PORT=="":
        PORT=80
    for i in URL:

        try:

            connection=http.client.HTTPConnection(domain)
            connection.request('GET',i)
            #connection.request(N,"/index.html")

            response=connection.getresponse()
            #print("server response :",response.status)
            p.append(response.status)
            #print("enebled methodes are :",response.getheader("allow"))

            connection.close()
        except ConnectionRefusedError:
            print("connection failed")
        s=[]
        for n in p:
            if n==200:
                s.append(URL)
    return s