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

English version:

I created my assignment within the healthcare topic. More specifically, I attempted to simulate the primary care of a smaller town, focusing on the necessary data for a patient to enter primary healthcare.

First, I created the 'Resident' class, in which I defined, among other things, the resident's name, date of birth, current address, and referred to the already created 'District' enum in this class as well. I generated getters and setters for all properties, as they will be needed in later processes.

From the existing 'Resident' class, I derived the 'Patient' class. I expanded the existing properties with two more. In the future, it will be necessary to provide information about allergies and the patient's previous surgeries, in addition to those already defined. I generated getters and setters for these last two properties.

Furthermore, the 'Patient' class forms the basis for writing XML. The program offers 5 options when running the XmlWriter. These include listing, adding, deleting, modifying patients, and the option to exit. Choosing the 'add' option prompts the system to request all the necessary data. Deletion and modification are possible by entering the name, but the system prompts for confirmation before proceeding. The data is collected based on the 'Patient' class (name, year of birth, address, district, allergy, surgery). I set a limit for the birth year. After entering the data, we can view the XML file itself using the 'XmlReader' class, which is automatically created after closing the '_XmlWriter' if we provided it with data.
