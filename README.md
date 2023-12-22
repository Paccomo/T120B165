
## Sprendžiamo uždavinio aprašymas

### Sistemos paskirtis

Projekto tikslas yra sutelkti visų vykdančių veiklą šaudyklų teikiamas paslaugas į vieną sistemą, tokiu būdu palengvinant klientams teikiamų paslaugų palyginimą bei užsisakymą.


Sistema sudaryta iš dviejų dalių – internetinės aplikacijos, bei aplikacijų programavimo sąsajos, komunikuojančios su duomenų baze.

Šaudyklų klientams platforma leis peržiūrėti informaciją apie šaudyklas, šių siūlomus ginklus ir instruktorius. Klientams registracijos ir prisijungimo nėra. Įmonių vadovai galės registruotis ir prisijungti prie sistemos, valdyti informaciją apie savo filialus. Administratorius tvirtins registracijas ir valdys įmones. Taip pat turės galimybę valdyti ir filialus, bei jų informaciją.
 ## <a name="_toc154158838"></a>**Funkciniai reikalavimai**
   Klientas galės:

- Peržiūrėti visas šaudyklas;
- Peržiūrėti visas įmones;
- Peržiūrėti visus siūlomus pabandymui ginklus;
- Peržiūrėti dirbančius instruktorius;

Įmonės vadovas galės:

- Užsiregistruoti ir prisijungti;
- Redaguoti savo įmonę;
- Pridėti ir šalinti šaudyklas;
- Keisti šaudyklų informaciją;
- Pridėti ir šalinti šaudyklose esančius ginklus;
- Keisti turimų ginklų informaciją;
- Pridėti ir šalinti šaudyklose dirbančius instruktorius;
- Keisti įmonėje dirbančių instruktorių informaciją.

Sistemos administratorius galės:

- Patvirtinti registracijas;
- Pridėti ir šalinti įmones;
- Keisti įmonių informaciją;
- Valdyti šaudyklas, ginklus, instruktorius taip pat, kaip ir įmonių vadovai.


 # <a name="_toc154158839"></a>**Sistemos architektūra**
   Sistema išskirstyta į dvi dalis – klientinę dalį ir serverio dalį. Klientinė dalis bus realizuota naudojant „React.js“. Serverio dalis – su „Java“ programavimo kalbai skirtu „Spring“ karkasu, bei „MySQL“ duomenų baze.


![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.001.png)



# Naudotojo sąsaja

## Naudotojo sąsajos projektas

Žemiau yra pateikiama suprojektuota naudotojo sąsaja. Ją sudaro šie langai:


- Prisijungimo langas;
- Registracijos langas;
- Pagrindinis langas;
- Esybių sąrašo langas;
- Konkrečios esybės langas.

![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.002.png)

![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.003.png)

![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.004.png)

![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.005.png)

![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.006.png)

 ## <a name="_toc154158842"></a>**Naudotojo sąsajos realizacija**
   Realizuotoje naudotojo sąsajoje yra šie langai:

- Prisijungimo langas;
- Registracijos langas;
- Pagrindinis langas;
- Įmonių langas;
- Naujos įmonės modalas;
- Įmonės redagavimo modalas;
- Konkrečios įmonės langas;
- Ginklų langas;
- Naujo ginklo modalas;
- Ginklo redagavimo modalas;
- Konkretaus ginklo langas;
- Šaudyklų langas;
- Naujos šaudyklos modalas;
- Šaudyklos redagavimo modalas;
- Konkrečios šaudyklos langas;
- Instruktorių langas;
- Naujo instruktoriaus modalas;
- Instruktoriaus redagavimo modalas;
- Konkretaus instruktoriaus langas.

![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.007.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.008.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.009.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.010.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.01png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.012.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.013.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.014.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.015.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.016.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.017.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.018.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.019.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.020.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.02png)
 


![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.022.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.023.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.024.png)



![](readmeIMG/Aspose.Words.2c46b85e-9329-45a4-919c-17c866a4ed1a.025.png)



 # <a name="_toc154158843"></a>**API specifikacija**
- Atsako formatas: JSON;
- Užklausų kiekis: neribojamas.
   ## <a name="_toc154158844"></a>**Autorizacijos API metodai**
     Žemiau yra pateikiami API metodai susiję su autorizacija .


|API metodas:|POST|
| - | - |
|Paskirtis:|Prisiregistruoti|
|Pasiekiama per:|api/v1/auth/register|
|Užklausos „header“ dali:|-|
|Užklausos struktūra:|<p>{</p><p>`	`"name": "t",</p><p>`	`"surname": "t",</p><p>`	`"email" : "t@email.cin",</p><p>`	`"password": "t"</p><p>}</p>|
|Atsakymo struktūra:|<p>{</p><p>`	`"access\_token": "{token}",</p><p>`	`"refresh\_token": "{token}"</p><p>}</p>|
|Galimi atsako kodai:|<p>- 200 (OK)</p><p>- 422 (Unprocessable Entity) – blogi naudotojo duomenys.</p>|



|API metodas:|POST|
| - | - |
|Paskirtis:|Prisijungti|
|Pasiekiama per:|api/v1/auth/authenticate|
|Užklausos „header“ dalis:|-|
|Užklausos struktūra:|<p>{</p><p>`	`"email" : "admin@email.cin",</p><p>`	`"password": "admin"</p><p>}</p>|
|Atsakymo struktūra:|<p>{</p><p>`	`"access\_token": "{token}",</p><p>`	`"refresh\_token": "{token}"</p><p>}</p>|
|Galimi atsako kodai:|<p>- 200 (OK)</p><p>- 403 (Forbidden) – blogi prisijungimo duomenys.</p>|



|API metodas:|POST|
| - | - |
|Paskirtis:|Atsijungti|
|Pasiekiama per:|api/v1/auth/logout|
|Užklausos „header“ dalis:|Authorization: bearer {token}|
|Užklausos struktūra:|-|
|Atsakymo struktūra:|-|
|Galimi atsako kodai:|- 200 (OK)|



|API metodas:|POST|
| - | - |
|Paskirtis:|Atnaujinti JWT žėtoną|
|Pasiekiama per:|api/v1/auth/refresh|
|Užklausos „header“ dalis:|Authorization: bearer {token}|
|Užklausos struktūra:||
|Atsakymo struktūra:|<p>{</p><p>`	`"access\_token": "{token}",</p><p>`	`"refresh\_token": "{token}"</p><p>}</p>|
|Galimi atsako kodai:|<p>- 200 (OK)</p><p>- 403 (Forbidden) – blogas „refresh“ žėtonas.</p>|

 ## <a name="_toc154158845"></a>**Įmonių API metodai**
   Žemiau yra pateikiami API metodai susiję su įmonės esybe.



|API metodas:|GET|
| - | - |
|Paskirtis:|Gauti visų įmonių sąrašą|
|Pasiekiama per:|api/v1/companies|
|Užklausos „header“ dalis:|-|
|Užklausos struktūra:|-|
|Atsakymo struktūra:|<p>[</p><p>`	`{</p><p>`		`"id": 1,</p><p>`		`"name": "UAB KBc",</p><p>`		`"address": "Baršausko g. 11",</p><p>`		`"postalCode": "LT-15151",</p><p>`		`"city": "Kaunas",</p><p>`		`"phoneNumber": "+37060253697",</p><p>`		`"email": "kb@gmail.com",</p><p>`		`"website": "https://kb.ly",</p><p>`		`"fk\_user": {</p><p>`			`"name": "user",</p><p>`			`"surname": "user"</p><p>`		`},</p><p>`		`"approved": true</p><p>`	`},</p><p>`	`{</p><p>`		`"id": 8,</p><p>`		`"name": "Šaudymo klubas",</p><p>`		`"address": "Taikos pr. 123",</p><p>`		`"postalCode": "LT-52528",</p><p>`		`"city": "Kaunas",</p><p>`		`"phoneNumber": "+37067985279",</p><p>`		`"email": "sklubas@gmail.com",</p><p>`		`"website": "",</p><p>`		`"fk\_user": {</p><p>`			`"name": "user",</p><p>`			`"surname": "user"</p><p>`		`},</p><p>`		`"approved": false</p><p>`	`}</p><p>]</p>|
|Galimi atsako kodai:|- 200 (OK)|



|API metodas:|GET|
| - | - |
|Paskirtis:|Gauti įmonės ginklų sąrašą|
|Pasiekiama per:|api/v1/companies/{id}/firearms|
|Užklausos „header“ dalis:|-|
|Užklausos struktūra:|-|
|Atsakymo struktūra:|<p>[</p><p>`	`{</p><p>`		`"id": 1,</p><p>`		`"manufacturer": "IMI",</p><p>`		`"model": "Desert Eagle",</p><p>`		`"caliber": ".50AE",</p><p>`		`"price": 0.5,</p><p>`		`"fk\_shootingRange": {</p><p>`			`"id": 1,</p><p>`			`"address": "Barsausko g. 5",</p><p>`			`"city": "Kaunas",</p><p>`			`"fk\_company": {</p><p>`				`"id": 1,</p><p>`				`"name": "UAB KBc"</p><p>`			`}</p><p>`		`},</p><p>`		`"picture": "https://www.drummencustomguns.com/5578-medium\_default/magnum-research-desert-eagle-44-mag-brushed-chrome.jpg"</p><p>`	`}</p><p>]</p>|
|Galimi atsako kodai:|<p>- 200 (OK)</p><p>- 404 (Not Found) – blogas įmonės „id“.</p>|



|API metodas:|GET|
| - | - |
|Paskirtis:|Gauti įmonės instruktorių sąrašą|
|Pasiekiama per:|api/v1/companies/{id}/instructors|
|Užklausos „header“ dalis:|-|
|Užklausos struktūra:|-|
|Atsakymo struktūra:|<p>[</p><p>`	`{</p><p>`		`"id": 1,</p><p>`		`"name": "Tadas",</p><p>`		`"surname": "Špokevičius",</p><p>`		`"fk\_shootingRange": {</p><p>`			`"id": 1,</p><p>`			`"address": "Barsausko g. 5",</p><p>`			`"city": "Kaunas",</p><p>`			`"fk\_company": {</p><p>`				`"id": 1,</p><p>`				`"name": "UAB KBc"</p><p>`			`}</p><p>`		`}</p><p>`	`},</p><p>`	`{</p><p>`		`"id": 2,</p><p>`		`"name": "Nedas",</p><p>`		`"surname": "Barkauskas",</p><p>`		`"fk\_shootingRange": {</p><p>`			`"id": 1,</p><p>`			`"address": "Barsausko g. 5",</p><p>`			`"city": "Kaunas",</p><p>`			`"fk\_company": {</p><p>`				`"id": 1,</p><p>`				`"name": "UAB KBc"</p><p>`			`}</p><p>`		`}</p><p>`	`}</p><p>]</p>|
|Galimi atsako kodai:|<p>- 200 (OK)</p><p>- 404 (Not Found) – blogas įmonės „id“.</p>|




|API metodas:|GET|
| - | - |
|Paskirtis:|Gauti įmonės informaciją|
|Pasiekiama per:|api/v1/companies/{id}|
|Užklausos „header“ dalis:|-|
|Užklausos struktūra:|-|
|Atsakymo struktūra:|<p>{</p><p>`	`"id": 8,</p><p>`	`"name": "Šaudymo klubas",</p><p>`	`"address": "Taikos pr. 123",</p><p>`	`"postalCode": "LT-52528",</p><p>`	`"city": "Kaunas",</p><p>`	`"phoneNumber": "+37067985279",</p><p>`	`"email": "sklubas@gmail.com",</p><p>`	`"website": "",</p><p>`	`"fk\_user": {</p><p>`		`"name": "user",</p><p>`		`"surname": "user"</p><p>`	`},</p><p>`	`"approved": false</p><p>}</p>|
|Galimi atsako kodai:|<p>- 200 (OK)</p><p>- 404 (Not Found) – blogas įmonės „id“.</p>|



|API metodas:|POST|
| - | - |
|Paskirtis:|Sukurti naują įmonę|
|Pasiekiama per:|api/v1/companies|
|Užklausos „header“ dalis:|Authorization: bearer {token}|
|Užklausos struktūra:|<p>{</p><p>`	`"name": "Šaudymo klubas",</p><p>`	`"address": "Taikos pr. 123",</p><p>`	`"city": "Kaunas",</p><p>`	`"phoneNumber": "+37067985279",</p><p>`	`"postalCode": "LT-52528",</p><p>`	`"website": "",</p><p>`	`"email": "sklubas@gmail.com",</p><p>`	`"fk\_user": 5</p><p>}</p>|
|Atsakymo struktūra:|<p>{</p><p>`	`"id": 8,</p><p>`	`"name": "Šaudymo klubas",</p><p>`	`"address": "Taikos pr. 123",</p><p>`	`"postalCode": "LT-52528",</p><p>`	`"city": "Kaunas",</p><p>`	`"phoneNumber": "+37067985279",</p><p>`	`"email": "sklubas@gmail.com",</p><p>`	`"website": "",</p><p>`	`"fk\_user": {</p><p>`		`"name": "user",</p><p>`		`"surname": "user"</p><p>`	`},</p><p>`	`"approved": true</p><p>}</p>|
|Galimi atsako kodai:|<p>- 201 (Created)</p><p>- 403 (Forbidden) – Draudžiama prieiga.</p><p>- 422 (Unprocessable Entity) – nekorektiški įmonės duomenys.</p>|



|API metodas:|PATCH|
| - | - |
|Paskirtis:|Redaguoti įmonės duomenis|
|Pasiekiama per:|api/v1/companies/{id}|
|Užklausos „header“ dalis:|Authorization: bearer {token}|
|Užklausos struktūra:|<p>{</p><p>`	`"name": "Šaudymo klubas",</p><p>`	`"address": "Taikos pr. 123",</p><p>`	`"city": "Kaunas",</p><p>`	`"phoneNumber": "+37067985279",</p><p>`	`"postalCode": "LT-52528",</p><p>`	`"website": "",</p><p>`	`"email": "sklubas@gmail.com",</p><p>`	`"fk\_user": 5</p><p>}</p>|
|Atsakymo struktūra:|<p>{</p><p>`	`"id": 8,</p><p>`	`"name": "Šaudymo klubas",</p><p>`	`"address": "Taikos pr. 123",</p><p>`	`"postalCode": "LT-52528",</p><p>`	`"city": "Kaunas",</p><p>`	`"phoneNumber": "+37067985279",</p><p>`	`"email": "sklubas@gmail.com",</p><p>`	`"website": "",</p><p>`	`"fk\_user": {</p><p>`		`"name": "user",</p><p>`		`"surname": "user"</p><p>`	`},</p><p>`	`"approved": true</p><p>}</p>|
|Galimi atsako kodai:|<p>- 201 (Created)</p><p>- 403 (Forbidden) – draudžiama prieiga.</p><p>- 404 (Not Found) – blogas įmonės „id“.</p><p>- 422 (Unprocessable Entity) – nekorektiški įmonės duomenys.</p>|



|API metodas:|DELETE|
| - | - |
|Paskirtis:|Pašalinti įmonę|
|Pasiekiama per:|api/v1/companies/{id}|
|Užklausos „header“ dalis:|Authorization: bearer {token}|
|Užklausos struktūra:|-|
|Atsakymo struktūra:|-|
|Galimi atsako kodai:|<p>- 204 (No Content)</p><p>- 403 (Forbidden) – draudžiama prieiga.</p><p>- 404 (Not Found) – blogas įmonės „id“.</p><p>- 409 (Conflict) – Negalima pašalinti, nes kiti duomenys įtraukia šią įmonę kaip išorinį raktą.</p>|

 ## <a name="_toc154158846"></a>**Šaudyklų API metodai**
   Žemiau yra pateikiami API metodai susiję su šaudyklos esybe.



|API metodas:|GET|
| - | - |
|Paskirtis:|Gauti visų šaudyklų sąrašą|
|Pasiekiama per:|api/v1/shootingRanges|
|Užklausos „header“ dalis:|-|
|Užklausos struktūra:|-|
|Atsakymo struktūra:|<p>[</p><p>`	`{</p><p>`		`"id": 1,</p><p>`		`"address": "Barsausko g. 5",</p><p>`		`"city": "Kaunas",</p><p>`		`"price": 0.08,</p><p>`		`"maxShooters": 2,</p><p>`		`"fk\_company": {</p><p>`			`"id": 1,</p><p>`			`"name": "UAB dddd"</p><p>`		`},</p><p>`		`"fk\_hours": {</p><p>`			`"monOpen": "09:02:00",</p><p>`			`"monClose": "21:05:00",</p><p>`			`"tueOpen": null,</p><p>`			`"tueClose": null,</p><p>`			`"wedOpen": null,</p><p>`			`"wedClose": null,</p><p>`			`"thurOpen": null,</p><p>`			`"thurClose": null,</p><p>`			`"friOpen": null,</p><p>`			`"friClose": null,</p><p>`			`"satOpen": null,</p><p>`			`"satClose": null,</p><p>`			`"sunOpen": null,</p><p>`			`"sunClose": null</p><p>`		`},</p><p>`		`"outdoor": true</p><p>`	`}</p><p>]</p>|
|Galimi atsako kodai:|- 200 (OK)|



|API metodas:|GET|
| - | - |
|Paskirtis:|Gauti šaudyklos informaciją|
|Pasiekiama per:|api/v1/shootingRanges/{id}|
|Užklausos „header“ dalis:|-|
|Užklausos struktūra:|-|
|Atsakymo struktūra:|<p>{</p><p>`	`"id": 1,</p><p>`	`"address": "Barsausko g. 5",</p><p>`	`"city": "Kaunas",</p><p>`	`"price": 0.08,</p><p>`	`"maxShooters": 2,</p><p>`	`"fk\_company": {</p><p>`		`"id": 1,</p><p>`		`"name": "UAB KBc"</p><p>`	`},</p><p>`	`"fk\_hours": {</p><p>`		`"monOpen": "09:02:00",</p><p>`		`"monClose": "21:05:00",</p><p>`		`"tueOpen": null,</p><p>`		`"tueClose": null,</p><p>`		`"wedOpen": null,</p><p>`		`"wedClose": null,</p><p>`		`"thurOpen": null,</p><p>`		`"thurClose": null,</p><p>`		`"friOpen": null,</p><p>`		`"friClose": null,</p><p>`		`"satOpen": null,</p><p>`		`"satClose": null,</p><p>`		`"sunOpen": null,</p><p>`		`"sunClose": null</p><p>`	`},</p><p>`	`"outdoor": true</p><p>}</p>|
|Galimi atsako kodai:|<p>- 200 (OK)</p><p>- 404 (Not Found) – blogas šaudyklos „id“.</p>|



|API metodas:|POST|
| - | - |
|Paskirtis:|Sukurti naują šaudyką|
|Pasiekiama per:|api/v1/shootingRanges|
|Užklausos „header“ dalis:|Authorization: bearer {token}|
|Užklausos struktūra:|<p>{</p><p>`	`"address": "Baltų pr. 19",</p><p>`	`"city": "Kaunas",</p><p>`	`"price": "10",</p><p>`	`"maxShooters": 2,</p><p>`	`"isOutdoor": true,</p><p>`	`"fk\_company": 4,</p><p>`	`"monOpen": "09:00",</p><p>`	`"monClose": "16:00",</p><p>`	`"tueOpen": null,</p><p>`	`"tueClose": null,</p><p>`	`"wedClose": "16:00",</p><p>`	`"wedOpen": "09:00",</p><p>`	`"thurClose": "16:00",</p><p>`	`"thurOpen": "09:00",</p><p>`	`"friClose": "16:00",</p><p>`	`"friOpen": "09:00",</p><p>`	`"satClose": "16:00",</p><p>`	`"satOpen": "09:00",</p><p>`	`"sunClose": "16:00",</p><p>`	`"sunOpen": "09:00"</p><p>}</p>|
|Atsakymo struktūra:|<p>{</p><p>`	`"id": 4,</p><p>`	`"address": "Baltų pr. 19",</p><p>`	`"city": "Kaunas",</p><p>`	`"price": 10.0,</p><p>`	`"maxShooters": 2,</p><p>`	`"fk\_company": {</p><p>`		`"id": 1,</p><p>`		`"name": "UAB dddd"</p><p>`	`},</p><p>`	`"fk\_hours": {</p><p>`		`"monOpen": "09:00:00",</p><p>`		`"monClose": "16:00:00",</p><p>`		`"tueOpen": null,</p><p>`		`"tueClose": null,</p><p>`		`"wedOpen": "09:00:00",</p><p>`		`"wedClose": "16:00:00",</p><p>`		`"thurOpen": "09:00:00",</p><p>`		`"thurClose": "16:00:00",</p><p>`		`"friOpen": "09:00:00",</p><p>`		`"friClose": "16:00:00",</p><p>`		`"satOpen": "09:00:00",</p><p>`		`"satClose": "16:00:00",</p><p>`		`"sunOpen": "09:00:00",</p><p>`		`"sunClose": "16:00:00"</p><p>`	`},</p><p>`	`"outdoor": true</p><p>}</p>|
|Galimi atsako kodai:|<p>- 201 (Created)</p><p>- 403 (Forbidden) – Draudžiama prieiga.</p><p>- 422 (Unprocessable Entity) – nekorektiški šaudyklos duomenys.</p>|



|API metodas:|PATCH|
| - | - |
|Paskirtis:|Redaguoti šaudyklos duomenis|
|Pasiekiama per:|api/v1/shootingRanges/{id}|
|Užklausos „header“ dalis:|Authorization: bearer {token}|
|Užklausos struktūra:|<p>{</p><p>`	`"address": "Baltų pr. 19",</p><p>`	`"city": "Kaunas",</p><p>`	`"price": "10",</p><p>`	`"maxShooters": 2,</p><p>`	`"isOutdoor": true,</p><p>`	`"fk\_company": 4,</p><p>`	`"monOpen": "09:00",</p><p>`	`"monClose": "16:00",</p><p>`	`"tueOpen": null,</p><p>`	`"tueClose": null,</p><p>`	`"wedClose": "16:00",</p><p>`	`"wedOpen": "09:00",</p><p>`	`"thurClose": "16:00",</p><p>`	`"thurOpen": "09:00",</p><p>`	`"friClose": "16:00",</p><p>`	`"friOpen": "09:00",</p><p>`	`"satClose": "16:00",</p><p>`	`"satOpen": "09:00",</p><p>`	`"sunClose": "16:00",</p><p>`	`"sunOpen": "09:00"</p><p>}</p>|
|Atsakymo struktūra:|<p>{</p><p>`	`"id": 4,</p><p>`	`"address": "Baltų pr. 19",</p><p>`	`"city": "Kaunas",</p><p>`	`"price": 10.0,</p><p>`	`"maxShooters": 2,</p><p>`	`"fk\_company": {</p><p>`		`"id": 1,</p><p>`		`"name": "UAB dddd"</p><p>`	`},</p><p>`	`"fk\_hours": {</p><p>`		`"monOpen": "09:00:00",</p><p>`		`"monClose": "16:00:00",</p><p>`		`"tueOpen": null,</p><p>`		`"tueClose": null,</p><p>`		`"wedOpen": "09:00:00",</p><p>`		`"wedClose": "16:00:00",</p><p>`		`"thurOpen": "09:00:00",</p><p>`		`"thurClose": "16:00:00",</p><p>`		`"friOpen": "09:00:00",</p><p>`		`"friClose": "16:00:00",</p><p>`		`"satOpen": "09:00:00",</p><p>`		`"satClose": "16:00:00",</p><p>`		`"sunOpen": "09:00:00",</p><p>`		`"sunClose": "16:00:00"</p><p>`	`},</p><p>`	`"outdoor": true</p><p>}</p>|
|Galimi atsako kodai:|<p>- 201 (Created)</p><p>- 403 (Forbidden) – draudžiama prieiga.</p><p>- 404 (Not Found) – blogas šaudyklos „id“.</p><p>- 422 (Unprocessable Entity) – nekorektiški šaudyklos duomenys.</p>|



|API metodas:|DELETE|
| - | - |
|Paskirtis:|Pašalinti šaudyklą|
|Pasiekiama per:|api/v1/shootingRanges/{id}|
|Užklausos „header“ dalis:|Authorization: bearer {token}|
|Užklausos struktūra:|-|
|Atsakymo struktūra:|-|
|Galimi atsako kodai:|<p>- 204 (No Content)</p><p>- 403 (Forbidden) – draudžiama prieiga.</p><p>- 404 (Not Found) – blogas šaudyklos „id“.</p><p>- 409 (Conflict) – Negalima pašalinti, nes kiti duomenys įtraukia šią šaudyklą kaip išorinį raktą.</p>|

 ## <a name="_toc154158847"></a>**Instruktorių API metodai**
   Žemiau yra pateikiami API metodai susiję su instruktoriaus esybe.



|API metodas:|GET|
| - | - |
|Paskirtis:|Gauti visų instruktorių sąrašą|
|Pasiekiama per:|api/v1/instructors|
|Užklausos „header“ dalis:|-|
|Užklausos struktūra:|-|
|Atsakymo struktūra:|<p>[</p><p>`	`{</p><p>`		`"id": 1,</p><p>`		`"name": "Tadas",</p><p>`		`"surname": "Špokevičius",</p><p>`		`"fk\_shootingRange": {</p><p>`			`"id": 1,</p><p>`			`"address": "Barsausko g. 5",</p><p>`			`"city": "Kaunas",</p><p>`			`"fk\_company": {</p><p>`				`"id": 1,</p><p>`				`"name": "UAB dddd"</p><p>`			`}</p><p>`		`}</p><p>`	`},</p><p>`	`{</p><p>`		`"id": 2,</p><p>`		`"name": "Nedas",</p><p>`		`"surname": "Barkauskas",</p><p>`		`"fk\_shootingRange": {</p><p>`			`"id": 1,</p><p>`			`"address": "Barsausko g. 5",</p><p>`			`"city": "Kaunas",</p><p>`			`"fk\_company": {</p><p>`				`"id": 1,</p><p>`				`"name": "UAB dddd"</p><p>`			`}</p><p>`		`}</p><p>`	`}</p><p>]</p>|
|Galimi atsako kodai:|- 200 (OK)|



|API metodas:|GET|
| - | - |
|Paskirtis:|Gauti instruktoriaus informaciją|
|Pasiekiama per:|api/v1/instructors/{id}|
|Užklausos „header“ dalis:|-|
|Užklausos struktūra:|-|
|Atsakymo struktūra:|<p>{</p><p>`	`"id": 1,</p><p>`	`"name": "Tadas",</p><p>`	`"surname": "Špokevičius",</p><p>`	`"fk\_shootingRange": {</p><p>`		`"id": 1,</p><p>`		`"address": "Barsausko g. 5",</p><p>`		`"city": "Kaunas",</p><p>`		`"fk\_company": {</p><p>`			`"id": 1,</p><p>`			`"name": "UAB dddd"</p><p>`		`}</p><p>`	`}</p><p>}</p>|
|Galimi atsako kodai:|<p>- 200 (OK)</p><p>- 404 (Not Found) – blogas instruktoriaus „id“.</p>|



|API metodas:|POST|
| - | - |
|Paskirtis:|Sukurti naują instruktorių|
|Pasiekiama per:|api/v1/instructors|
|Užklausos „header“ dalis:|Authorization: bearer {token}|
|Užklausos struktūra:|<p>{</p><p>`	`"name": "Algirdas",</p><p>`	`"surname": "Janulaitis",</p><p>`	`"fk\_shootingRange": 1</p><p>}</p>|
|Atsakymo struktūra:|<p>{</p><p>`	`"id": 3,</p><p>`	`"name": "Algirdas",</p><p>`	`"surname": "Janulaitis",</p><p>`	`"fk\_shootingRange": {</p><p>`		`"id": 1,</p><p>`		`"address": "Barsausko g. 5",</p><p>`		`"city": "Kaunas",</p><p>`		`"fk\_company": {</p><p>`			`"id": 1,</p><p>`			`"name": "UAB dddd"</p><p>`		`}</p><p>`	`}</p><p>}</p>|
|Galimi atsako kodai:|<p>- 201 (Created)</p><p>- 403 (Forbidden) – Draudžiama prieiga.</p><p>- 422 (Unprocessable Entity) – nekorektiški instruktoriaus duomenys.</p>|



|API metodas:|PATCH|
| - | - |
|Paskirtis:|Redaguoti instruktoriaus duomenis|
|Pasiekiama per:|api/v1/ instructors/{id}|
|Užklausos „header“ dalis:|Authorization: bearer {token}|
|Užklausos struktūra:|<p>{</p><p>`	`"name": "Algirdas",</p><p>`	`"surname": "Janulaitis",</p><p>`	`"fk\_shootingRange": 1</p><p>}</p>|
|Atsakymo struktūra:|<p>{</p><p>`	`"id": 3,</p><p>`	`"name": "Algirdas",</p><p>`	`"surname": "Janulaitis",</p><p>`	`"fk\_shootingRange": {</p><p>`		`"id": 1,</p><p>`		`"address": "Barsausko g. 5",</p><p>`		`"city": "Kaunas",</p><p>`		`"fk\_company": {</p><p>`			`"id": 1,</p><p>`			`"name": "UAB dddd"</p><p>`		`}</p><p>`	`}</p><p>}</p>|
|Galimi atsako kodai:|<p>- 201 (Created)</p><p>- 403 (Forbidden) – draudžiama prieiga.</p><p>- 404 (Not Found) – blogas instruktoriaus „id“.</p><p>- 422 (Unprocessable Entity) – nekorektiški instruktoriaus duomenys.</p>|



|API metodas:|DELETE|
| - | - |
|Paskirtis:|Pašalinti instruktorių|
|Pasiekiama per:|api/v1/instructors/{id}|
|Užklausos „header“ dalis:|Authorization: bearer {token}|
|Užklausos struktūra:|-|
|Atsakymo struktūra:|-|
|Galimi atsako kodai:|<p>- 204 (No Content)</p><p>- 403 (Forbidden) – draudžiama prieiga.</p><p>- 404 (Not Found) – blogas instruktoriaus „id“.</p>|

 ## <a name="_toc154158848"></a>**Ginklų API metodai**
   Žemiau yra pateikiami API metodai susiję su įmonės esybe.



|API metodas:|GET|
| - | - |
|Paskirtis:|Gauti visų ginklų sąrašą|
|Pasiekiama per:|api/v1/firearms|
|Užklausos „header“ dalis:|-|
|Užklausos struktūra:|-|
|Atsakymo struktūra:|<p>[</p><p>`	`{</p><p>`		`"id": 1,</p><p>`		`"manufacturer": "IMI",</p><p>`		`"model": "Desert Eagle",</p><p>`		`"caliber": ".50AE",</p><p>`		`"price": 0.5,</p><p>`		`"fk\_shootingRange": {</p><p>`			`"id": 1,</p><p>`			`"address": "Barsausko g. 5",</p><p>`			`"city": "Kaunas",</p><p>`			`"fk\_company": {</p><p>`				`"id": 1,</p><p>`				`"name": "UAB dddd"</p><p>`			`}</p><p>`		`},</p><p>`		`"picture": "https://www.drummencustomguns.com/5578-medium\_default/magnum-research-desert-eagle-44-mag-brushed-chrome.jpg"</p><p>`	`}</p><p>]</p>|
|Galimi atsako kodai:|- 200 (OK)|



|API metodas:|GET|
| - | - |
|Paskirtis:|Gauti ginklo informaciją|
|Pasiekiama per:|api/v1/firearms/{id}|
|Užklausos „header“ dalis:|-|
|Užklausos struktūra:|-|
|Atsakymo struktūra:|<p>{</p><p>`	`"id": 1,</p><p>`	`"manufacturer": "IMI",</p><p>`	`"model": "Desert Eagle",</p><p>`	`"caliber": ".50AE",</p><p>`	`"price": 0.5,</p><p>`	`"fk\_shootingRange": {</p><p>`		`"id": 1,</p><p>`		`"address": "Barsausko g. 5",</p><p>`		`"city": "Kaunas",</p><p>`		`"fk\_company": {</p><p>`			`"id": 1,</p><p>`			`"name": "UAB dddd"</p><p>`		`}</p><p>`	`},</p><p>`	`"picture": "https://www.drummencustomguns.com/5578-medium\_default/magnum-research-desert-eagle-44-mag-brushed-chrome.jpg"</p><p>}</p>|
|Galimi atsako kodai:|<p>- 200 (OK)</p><p>- 404 (Not Found) – blogas ginklo „id“.</p>|



|API metodas:|POST|
| - | - |
|Paskirtis:|Sukurti naują ginklą|
|Pasiekiama per:|api/v1/firearms|
|Užklausos „header“ dalis:|Authorization: bearer {token}|
|Užklausos struktūra:|<p>{</p><p>`	`"manufacturer": "PM",</p><p>`	`"model": "Makarov",</p><p>`	`"caliber": "9x18mm",</p><p>`	`"picture": null,</p><p>`	`"price": "10.99",</p><p>`	`"fk\_shootingRange": 1</p><p>}</p>|
|Atsakymo struktūra:|<p>{</p><p>`	`"id": 3,</p><p>`	`"manufacturer": "PM",</p><p>`	`"model": "Makarov",</p><p>`	`"caliber": "9x18mm",</p><p>`	`"price": 10.99,</p><p>`	`"fk\_shootingRange": {</p><p>`		`"id": 1,</p><p>`		`"address": "Barsausko g. 5",</p><p>`		`"city": "Kaunas",</p><p>`		`"fk\_company": {</p><p>`			`"id": 1,</p><p>`			`"name": "UAB dddd"</p><p>`		`}</p><p>`	`},</p><p>`	`"picture": null</p><p>}</p>|
|Galimi atsako kodai:|<p>- 201 (Created)</p><p>- 403 (Forbidden) – Draudžiama prieiga.</p><p>- 422 (Unprocessable Entity) – nekorektiški ginklo duomenys.</p>|



|API metodas:|PATCH|
| - | - |
|Paskirtis:|Redaguoti ginklo duomenis|
|Pasiekiama per:|api/v1/firearms/{id}|
|Užklausos „header“ dalis:|Authorization: bearer {token}|
|Užklausos struktūra:|<p>{</p><p>`	`"manufacturer": "PM",</p><p>`	`"model": "Makarov",</p><p>`	`"caliber": "9x18mm",</p><p>`	`"picture": null,</p><p>`	`"price": "10.99",</p><p>`	`"fk\_shootingRange": 1</p><p>}</p>|
|Atsakymo struktūra:|<p>{</p><p>`	`"id": 3,</p><p>`	`"manufacturer": "PM",</p><p>`	`"model": "Makarov",</p><p>`	`"caliber": "9x18mm",</p><p>`	`"price": 10.99,</p><p>`	`"fk\_shootingRange": {</p><p>`		`"id": 1,</p><p>`		`"address": "Barsausko g. 5",</p><p>`		`"city": "Kaunas",</p><p>`		`"fk\_company": {</p><p>`			`"id": 1,</p><p>`			`"name": "UAB dddd"</p><p>`		`}</p><p>`	`},</p><p>`	`"picture": null</p><p>}</p>|
|Galimi atsako kodai:|<p>- 201 (Created)</p><p>- 403 (Forbidden) – draudžiama prieiga.</p><p>- 404 (Not Found) – blogas ginklo „id“.</p><p>- 422 (Unprocessable Entity) – nekorektiški ginklo duomenys.</p>|



|API metodas:|DELETE|
| - | - |
|Paskirtis:|Pašalinti ginklą|
|Pasiekiama per:|api/v1/firearm/{id}|
|Užklausos „header“ dalis:|Authorization: bearer {token}|
|Užklausos struktūra:|-|
|Atsakymo struktūra:|-|
|Galimi atsako kodai:|<p>- 204 (No Content)</p><p>- 403 (Forbidden) – draudžiama prieiga.</p><p>- 404 (Not Found) – blogas ginklo „id“.</p>|
