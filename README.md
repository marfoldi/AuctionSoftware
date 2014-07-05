AuctionSoftware
===============

Programozási Technilógia 2, 2013/2014, tavaszi félév, első beadandó:

Készítsünk programot, amely aukciók kezelését teszi lehetővé.
A programot használhatják az aukciók meghirdetői és a licitálók is,
de az egyszerűség kedvéért elegendő egy közös felhasználói felületet készíteni.

A program jelenítse meg az aukciókon licitre meghirdetett tárgyakat
(elnevezés, kategória, kezdő licitösszeg, lezárás dátuma, eddigi legmagasabb licit (ha volt már)).
Egy tárgyat kiválasztva a program jelenítse meg az arra vonatkozó összes licitet (tárgy neve, licitáló neve, dátum, összeg).
Legyen lehetőség új tárgy felvitelére, megadva az elnevezést, a kategóriát, a kezdő licitösszeget (minimum 100 HUF),
és a lezárás dátumát (csak jövőbeni dátum lehet).
Legyen lehetőség új licitet tenni a licitáló nevének, a tárgynak, és az összegnek megadásával.
Csak olyan tárgya lehet licitet tenni, amely még nem lezárt.
Ha a kiválasztott tárgya még nem tettek licitet, akkor az összegnek legalább a kezdő licitösszegnek kell lennie;
egyébként a korábbi liciteknél nagyobbnak kell lennie. A program automatikusan rögzítse a licit dátumát.
A liciteket módosítani, visszavonni ne lehessen.
Az adatbázis az alábbi adatokat tárolja (ezek még nem feltétlenül a fizikai adattáblák):

Kategóriák: név;
Tárgyak: név, kategória, kezdő licitösszeg, lezárás dátuma;
Licitek: tárgy, licitelő neve, dátum, összeg.
