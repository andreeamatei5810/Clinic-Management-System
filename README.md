# Clinic-Management-System
Proiectul are ca tema un sistem de gestiune al unei clinici.

In cadrul proiectului, am urmatoarele clase definite:
- Clasa abstracta User, din care sunt mostenite clasele Admin,Doctor si Patient 
- Clasa Ward ce reprezinta sectiile clinicii
- Clasa Medicine cu medicamentele disponibile in clinica, ce pot fi prescrise pacientilor
- Clasa EssentialTest, din care se mosteneste clasa AdvancedTest, din care la randul ei se mosteneste clasa TotalTest, 3 clase ce reprezinta pachete de analize pe care le pot efectua un pacient
- Clasa Appointment ce reprezinta programarea unui pacient la un doctor, unde este utilizat conceptul de compunere: avem cate o instanta a claselor Patient, Doctor, Diagnosis si Treatment. De asemenea, are si o instanta a unei clase de tip Enum, ce retine statusul la care se afla programarea(confirmata, anulata, terminata etc).
- Clasa Treatment, tratamentul prescris unui pacient de catre un doctor in cadrul unei programari, ce contine un HashMap<Medicine,Integer>, reprezentand medicamentele prescrise si cantitatea lor(pentru a stabili un pret).
- Clasa Diagnosis, diagnosticul  stabilit unui pacient de catre un doctor in cadrul unei programari, ce are in cadrul ei o instanta aunei clase de tip Enum. pentru a descrie severitatea problemei.

In aplicatie, in functie de rol, se pot efectua urmatoarele actiuni/interogari:

Comune tuturor userilor
- Sa vada rezultatele unor analize
- Sa efectueze setari legate de cont (schimbare parola, telefon)
- Sa faca o programare
- Sa vada detalii despre o programare

Pacient
- Sa vada programari viitoare ( si doctorul)

Doctor
- Sa stabileasca un diagnostic
- Sa prescrie un tratament

Admin
- Sa schimbe statusul unei programari (si doctorul)
- Sa vada toate sectiile
- Sa vada toate medicamentele
- Sa adauge un doctor
- Sa adauge un pacient
