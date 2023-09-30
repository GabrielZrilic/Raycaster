# Raycaster - Završni rad

Paragraf (uvod)

## Sadržaj
- [Raycaster](#raycaster)
    - [Instalacija](#instalacija)
    - [Korištenje](#korištenje)
    - [Korištene tehnologije](#korištene-tehnologije)
    - [Struktura programa](#struktura-programa)
    - [Raycasting](#raycasting)
- [Zaključak](#zaključak)
- [Literatura](#literatura)
- [Prilozi](#prilozi)

## Raycaster

### Instalacija

Za pokretanje ovog programa potrebno je imati instaliranu Java JRE (Java Runtime Environment) i neki od popularnih uređivača koda kao što su Visual Studio Code, IntelliJ IDEA, IntelliJ IDEA, ili Eclipse.

Kod programa možete pokrenuti iz svog odabranog uređivača koda ili jednostavno pokrenuti izvršnu datoteku `Raycasting.jar`. Izvorni kod projekta dostupan je na [GitHub repozitoriju](https://github.com/GabrielZrilic/Raycaster), gdje ga možete preuzeti i prilagoditi prema svojim potrebama.

### Korištenje

Ovaj program služi za demonstraciju raycasting algoritma i pruža praktičan primjer upotrebe Java programskog jezika.

Kada pokrenete aplikaciju, otvorit će se prozor (vidi Sliku 1) koji sadrži tablicu s raznim gumbovima, uključujući i gumb `Create`. Ovaj prozor predstavlja sučelje za stvaranje mape kroz koju će se kamera simulirano kretati.

![Slika 1](https://github.com/GabrielZrilic/Raycaster/.images/Slika1.png)

Klikanjem na gumbe, možete jednostavno dodavati ili uklanjati zidove na mapi (vidi Sliku 2). Sustav zidova je organiziran u tablici, što je ključno za ispravno funkcioniranje raycasting (DDA) algoritma.

![Slika 2](https://github.com/GabrielZrilic/Raycaster/.images/Slika2.png)

Kada završite s postavljanjem zidova, jednostavno pritisnite gumb `Create`. Prozor za uređivanje zidova će se zatvoriti, a umjesto njega će se pojaviti novi prozor s 3D prikazom mape koju ste prethodno stvorili. Sada možete kontrolirati kameru koristeći tipke sa strelicama na tipkovnici.

![Slika 3](https://github.com/GabrielZrilic/Raycaster/.images/Slika3.png)

Ovaj program pruža zanimljivu vizualizaciju rada raycasting algoritma i omogućuje vam da eksperimentirate s različitim mapama kako biste bolje razumjeli njegovo funkcioniranje.

### Korištene tehnologije

Ovaj projekt koristi nekoliko ključnih tehnologija kako bi omogućio raycasting simulaciju:

- **Java**: Osnovni programski jezik u kojem je napisana aplikacija. Java omogućuje platformsku neovisnost i lakoću razvoja.

- **Visual Studio Code (VSCode)**: Uređivač koda koji je korišten za razvoj Java aplikacije. VSCode je popularan alat među programerima zbog svoje prilagodljivosti i ekstenzibilnosti.

- **GitHub**: Za verzioniranje koda i suradnju na projektu korišten je GitHub repozitorij. Ovo omogućuje timski rad, praćenje promjena i upravljanje projektom.

- **FlatLaf**: Tema za Java Swing koja pruža moderno i privlačno korisničko sučelje. FlatLaf je odabran kako bi se poboljšala estetika aplikacije i omogućila bolja interakcija s korisnicima.

Ove tehnologije zajedno omogućuju stvaranje aplikacije koja demonstrira raycasting koncepte i pruža korisnicima privlačno i suvremeno korisničko sučelje zahvaljujući FlatLaf temi za Java Swing.

### Struktura programa

Struktura programa je jednostavna. Pri pokretanju programa pokreće se main klasa App. Ona sadrži mainWindow koja sa funkcijom startMap pokreće 3d prikaz. Sve konstante su u klasi `Constants` i njezine vrijednosti su postavljene kao `public static`.

Klasa `RaycastingWindow` sadržava, osim vrijednosti i funkcija za kontrolu kamere, funkciju za crtanje `drawRays(Graphics2D g2d);` koja koristeći funkcije klase `Raycasting` crta vertikalne linije.

Klasa `Camera` sadržava podatke poput: pozicije, rotacije, vidno polje.

![Slika 4](https://github.com/GabrielZrilic/Raycaster/.images/Slika4.svg)

### Pomicanje i vektori


### Raycasting


## Zaključak
## Literatura
- [Wikipedia - Ray casting](https://en.wikipedia.org/wiki/Ray_casting)
- [Lode's Computer Graphics Tutorial](https://lodev.org/cgtutor/raycasting.html)
- [Ray casting fully explained. Pseudo 3D game](https://www.youtube.com/watch?v=g8p7nAbDz6Y)
## Prilozi