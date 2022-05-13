Beadandómat az egészségügy témakörön belül csináltam. Pontosabban egy kisebb város háziorvosi
ellátását próbáltam olyan szempontból megvalósítani, hogy milyen adatok szükségesek ahhoz, 
hogy a páciens bekerüljön egy háziorvosi ellátásba.
    
Először létrehoztam a _Lakos_ osztályt. Ebben definiáltam többek között a lakos nevét, 
születési dátumát, jelenlegi lakcímét, továbbá a már létrehozott _Korzet_ enumra is ebben az
az osztályban hivatkozom. Létrehoztam az összes tulajdonsághoz getterket illetve settereket,
mert a későbbi folyamatok során szükség lesz rájuk.

A már meglévő _Lakos_ osztályból származtattam a _Paciens_ osztályt. Kibővítettem két tulajdonsággal
az eddigieket. Későbbiekben már meg kell adni az allergiát, illetve a páciens korábbi műtéteit,
az előbb definiáltak mellett. Utóbbi kettő tulajdonsághoz gettereket, settereket generáltam.

A továbbiakban a _Paciens_ osztály adja az alapját az xml írásának. 5 opciót kínál fel a program az
_XmlWriter_ futtatása során. A páciensek listázását, hozzáadását, törlését, módosítását és kilépés 
lehetőséget. Hozzáadás opciót választva a rendszer bekéri az összes szükséges adatot, törölni és 
módosítani a név bevitelével lehet, de erre is rákérdez előtte a rendszer. Az adatokat a _Paciens_
osztály alapján kéri be (név, születési év, lakcím, körzet, allergia, műtét). A születési évre korlátot
raktam. Miután bevittük az adatokat, az _XmlReader_ osztály segítségével meg is tudjuk tekinteni magát 
az xml fájlt, ami automatikusan létrejön az _XmlWriter_lezárása után, ha adtunk neki adatokat.