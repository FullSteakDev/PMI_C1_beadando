package Egeszsegugy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class XmlWriter {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Paciens> paciensek = XmlReader.readPaciensekFromXml("src/main/resources/paciensek.xml");

        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1 -> listPaciensek(paciensek);
                case 2 -> addNewPaciens(paciensek);
                case 3 -> modifyPaciens(paciensek);
                case 4 -> deletePaciens(paciensek);
            }
            System.out.println("1 - Páciensek kilistázása\r\n2 - Új páciens hozzáadása\r\n"
                    + "3 - Páciens módosítása\r\n4 - Páciens törlése\r\n0 - Kilépés");
            try{
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 0 || choice > 4) {
                    System.out.println("Nincs ilyen opció.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Nincs ilyen opció.");
                scanner.nextLine();
            }
        }

        savePaciensekToXml(paciensek, "src/main/resources/paciensek.xml");
    }

    private static void modifyPaciens(ArrayList<Paciens> paciensek) {
        System.out.print("Kérem adja meg a páciens nevét, akit módosítani szeretne: ");
        String name = scanner.nextLine();
        for (Paciens paciens : paciensek) {
            if (paciens.getName().equals(name)) {
                int birthYear = inputBirthYear();
                System.out.print("Kérem adja meg a páciens lakcímét: ");
                String address = scanner.nextLine();
                Korzet korzet = inputKorzet();
                System.out.print("Kérem adja meg, mire allergiás a páciens: ");
                String allergia = scanner.nextLine();
                System.out.print("Kérem adja meg a páciens korábbi műtét, illetve műtéteit vesszővel elválasztva: ");
                String mutet = scanner.nextLine();

                paciensek.set(paciensek.indexOf(paciens), new Paciens(name, address, birthYear, korzet, allergia, mutet));
                System.out.println("Sikeresen módosította a páciens adatát.");
                return;
            }
        }
        System.out.println("Iyen névvel nem szerepel páciens a listán.");
    }

    private static void deletePaciens(ArrayList<Paciens> paciensek) {
        System.out.print("Kérem adja meg a páciens nevét, akit törölni szeretne: ");
        String name = scanner.nextLine();
        for (Paciens paciens : paciensek) {
            if (paciens.getName().equals(name)) {
                paciensek.remove(paciens);
                System.out.println("Sikeresen törölte a pácienst.");
                return;
            }
        }
        System.out.println("Iyen névvel nem szerepel páciens a listán.");
    }

    private static void addNewPaciens(ArrayList<Paciens> paciensek) {
        System.out.print("Kérem adja meg a páciens nevét: ");
        String name = scanner.nextLine();
        int birthYear = inputBirthYear();
        System.out.print("Kérem adja meg a páciens lakcímét: ");
        String address = scanner.nextLine();
        Korzet korzet = inputKorzet();
        System.out.print("Kérem adja meg, mire allergiás a páciens: ");
        String allergia = scanner.nextLine();
        System.out.print("Kérem adja meg, milyen műtéten/műtéteken esett át a páciens: ");
        String mutet = scanner.nextLine();

        paciensek.add(new Paciens(name, address, birthYear, korzet, allergia, mutet));
    }

    private static int inputBirthYear() {
        int birthYear = 0;
        while (1914 > birthYear || 2022 < birthYear) {
            try {
                System.out.print("Kérem adja meg a páciens születési évét: ");
                birthYear = scanner.nextInt();
                scanner.nextLine();
                if (birthYear < 1914 || birthYear > 2022) {
                    System.out.println("A születési év nem lehet korábbi" +
                            " 1914-né1 vagy későbbi 2022-nél.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Nem helyes a születési év.");
                scanner.nextLine();
            }
        }
        return birthYear;
    }


            private static Korzet inputKorzet () {
                Korzet korzet = Korzet.ELSO;
                String korzetString = "";
                while (korzetString.isEmpty()) {
                    try {
                        System.out.print("Kérem adja meg a páciens körzetét: ");
                        korzetString = scanner.nextLine();
                        korzet = Korzet.valueOf(korzetString.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Nem megengedett korzet(Kérem az alábbi módon próbálja újra: ELSO, MASODIK....HETEDIK).");
                        korzetString = "";
                    }
                }
                return korzet;
            }

            private static void listPaciensek (ArrayList < Paciens > paciensek) {
                System.out.println(paciensek);
            }

            public static void savePaciensekToXml (ArrayList < Paciens > paciensek, String filepath){
                try {
                    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                    Element rootElement = document.createElement("paciensek");
                    document.appendChild(rootElement);

                    for (Paciens paciens : paciensek) {
                        Element userElement = document.createElement("paciens");
                        rootElement.appendChild(userElement);
                        createChildElement(document, userElement, "name", paciens.getName());
                        createChildElement(document, userElement, "address", paciens.getAddress());
                        createChildElement(document, userElement, "birthYear", String.valueOf(paciens.getBirthYear()));
                        createChildElement(document, userElement, "korzet", paciens.getKorzet().toString());
                        createChildElement(document, userElement, "allergia", paciens.getAllergia());
                        createChildElement(document, userElement, "mutet", paciens.getMutet());
                    }

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();

                    DOMSource source = new DOMSource(document);
                    StreamResult result = new StreamResult(new FileOutputStream(filepath));

                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.transform(source, result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private static void createChildElement (Document document, Element parent,
                    String tagName, String value){
                Element element = document.createElement(tagName);
                element.setTextContent(value);
                parent.appendChild(element);
            }
        }
