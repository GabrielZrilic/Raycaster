# Raycaster - Završni rad

U digitalnom svijetu, vizualna reprezentacija prostora ima ključnu ulogu. Raycasting, tehnika koja omogućuje prikazivanje 2D prostora u okružju koje simulira 3D dimenziju, nudi jedinstven pristup tom zadatku. Osim algoritma raycastinga, ovaj rad istražuje tehnologije koje su korištene.

## Sadržaj
- [Raycaster](#raycaster)
    - [Instalacija](#instalacija)
    - [Korištenje](#korištenje)
    - [Java](#java)
        - [Tipovi podataka](#tipovi-podataka)
        - [Swing](#swing)
    - [Struktura programa](#struktura-programa)
    - [Pomicanje, rotacija i vektori](#pomicanje-rotacija-i-vektori)
        - [Pomak](#pomak)
        - [Rotacija](#rotacija)
    - [Raycasting](#raycasting)
        - [Stvaranje zrake](#stvaranje-zrake)
        - [Prikaz zrake](#prikaz-zrake)
- [Zaključak](#zaključak)
- [Literatura](#literatura)
- [Prilozi](#prilozi)

## Raycaster

### Instalacija

Za pokretanje ovog programa potrebno je imati instaliranu Java JRE (Java Runtime Environment) i neki od popularnih uređivača koda kao što su Visual Studio Code, IntelliJ IDEA, IntelliJ IDEA, ili Eclipse.

Kod programa možete pokrenuti iz svog odabranog uređivača koda ili jednostavno pokrenuti izvršnu datoteku `Raycasting.jar`.

### Korištenje

Ovaj program služi za demonstraciju raycasting algoritma i pruža praktičan primjer upotrebe Java programskog jezika.

Kada pokrenete aplikaciju, otvorit će se prozor (vidi Sliku 1) koji sadrži tablicu s raznim gumbovima, uključujući i gumb `Create`. Ovaj prozor predstavlja sučelje za stvaranje mape kroz koju će se kamera simulirano kretati.

![Slika 1](https://github.com/GabrielZrilic/Raycaster/blob/master/.images/Slika1.png)

Klikanjem na gumbe, možete jednostavno dodavati ili uklanjati zidove na mapi (vidi Sliku 2). Sustav zidova je organiziran u tablici, što je ključno za ispravno funkcioniranje raycasting (DDA) algoritma.

![Slika 2](https://github.com/GabrielZrilic/Raycaster/blob/master/.images/Slika2.png)

Kada završite s postavljanjem zidova, jednostavno pritisnite gumb `Create`. Prozor za uređivanje zidova će se zatvoriti, a umjesto njega će se pojaviti novi prozor s 3D prikazom mape koju ste prethodno stvorili. Sada možete kontrolirati kameru koristeći tipke sa strelicama na tipkovnici.

![Slika 3](https://github.com/GabrielZrilic/Raycaster/blob/master/.images/Slika3.png)

Ovaj program pruža zanimljivu vizualizaciju rada raycasting algoritma i omogućuje vam da eksperimentirate s različitim mapama kako biste bolje razumjeli njegovo funkcioniranje.

### Java
Java je programski jezik koji je razvijen od tvrtke Sun Microsystems. To je objrktno orijentinan programski jezik, što znači da su podaci i funkcije podjeljeni u klase, koje omogućuju stvaranje objekata, nasljeđivanje, enkapsulacija, polimorfizam i apstrakciju.

Da bi se nastavak rada bolje razumio, u nastavku će biti opisane osnove Java programskog jezika.

#### Tipovi podataka
Java, kao strogo tipizirani programski jezik, zahtijeva da svaka varijabla ima određeni tip podataka koji definira vrstu vrijednosti koja se može pohraniti u toj varijabli. Ovo je važno jer pomaže u provjeri valjanosti i sigurnosti kod izvođenja koda. Neke od osnovnih tipova podataka su:
- Cijeli broj (`int`) - Ovo su tipovi koji predstavljaju cijele brojeve. Na primjer, int se koristi za pohranu cjelobrojnih vrijednosti kao što su 5, -10, 1000 itd.
```java
    int number = 5;
```
- Realni brojevi (`double`, `float`) - Ovi tipovi podataka koriste se za pohranu decimalnih brojeva. `double` se često koristi za veću preciznost, dok se `float` koristi kada je potrebno manje memorije.
```java
    double number1 = 5.5;
    float number2 = 5.5;
```
- Znakovi (`char`) - Tip `char` koristi se za pohranu pojedinačnih znakova, kao što su slova, brojevi i posebni znakovi.
```java
    char ch = 'a';
```
- Logički tip (`boolean`) - Ovaj tip podataka ima samo dvije moguće vrijednosti: `true` ili `false`. Koristi se za provođenje uvjeta i logičkih operacija.
```java
    boolean isRunning = true;
```
- Nizovi - Nizovi su skupovi istog tipa podataka koji omogućavaju pohranu više vrijednosti istog tipa.
```java
    int[] array = new int[5];   // Stvara niz s 5 elemenata
```
- Objekti - Java omogućava kreiranje vlastitih tipova podataka pomoću klasa. Ovo su tzv. "referentni" tipovi podataka jer se ne pohranjuju izravno u memoriju kao cjelobrojni tipovi, već se koriste reference na objekte. Klasa sadrži konstruktor, podatke i funkcije.
```java
    class MainClass {
        public static void main(String[] args) {
            Rectangle rec = new Rectangle(2, 5.5);  // Stvara se objekt
            
            double area, volume;

            rec.a = 4.5;                    // Pristupa se i miljenja vrijednost

            area = rec.area();              // Površina se sprema u area
            volume = rec.volume(7.2);       // Volumen se sprema u volume
        }
    }

    class Rectangle {
        public double a;                    // Vrijednosti klase su public, bilo
        public double b;                    // gdje se može pristupjeti

        Rectangle(double a, double b) {     // Deklaracija konstraktora
            this.a = a;                     // 'this' označava varijablu klase i 
                                            // postavlja je na argument
            this.b = b;
        }

        double area() {                     // Funkcija koristeći podatke iz klase
            return a*b;                     // 'vraća' površinu četverokuta
        }

        double volume(double h) {           // Funkcija vraća volumen tijela kojem
            return area()*h;                // kojem je baza ovaj četverokut
        }
    }
```

#### Swing
Swing je *widget toolkit* za Javu. Nudi jednostavno stvaranje grafičkih sučelja. Prethodnik Swing-a, AWT je bio platformski ovisan i to je stvaralo probleme u razvijanju programa, jer je sama svrha Jave bila da bude platformski neovisna.

Nasljednik Swing-a je JavaFX zbog toga jer je bio lakši za uređivanje, modernije kontrole i korištenje SceneBuilder-a, grafičkog stvaranja korisničkog sučelja.

Primjer korištenja swinga:

```java
    public class MainClass {
    
        public static void main(String[] args) {
            // Stvori prozor i definiraj izlaznu operaciju
            JFrame frame = new JFrame("Layout");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
            // Stvori gumbe
            JButton jb1 = new JButton("Gumb 1");      
            JButton jb2 = new JButton("Gumb 2");
            JButton jb3 = new JButton("Gumb 3");          
             
            // Stvori panel na kojem će se nalaziti gumbi, dodaj gumbe
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            panel.add(jb1);
            panel.add(jb2);
            panel.add(jb3);
             
            // Dodaj panel u prozor i napravi prozor vidljivim
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);     
        }
    
    }
```

### Struktura programa

Struktura programa je jednostavna. Prilikom pokretanja programa, izvršava se glavna klasa App. Ova klasa sadrži `mainWindow` koja, pomoću funkcije `startMap`, inicijalizira 3D prikaz. Sve konstante su definirane u klasi `Constants` i postavljene su kao `public static`.

Klasa `RaycastingWindow` sadrži vrijednosti i funkcije za upravljanje kamerom, kao i funkciju za crtanje `drawRays(Graphics2D g2d);`. Ova funkcija koristi funkcije iz klase `Raycasting` kako bi iscrtala vertikalne linije.

Klasa `Camera` sadrži podatke o poziciji, rotaciji i vidnom polju kamere, zajedno s funkcijama za pomicanje i rotaciju kamere.

![Slika 4](https://github.com/GabrielZrilic/Raycaster/blob/master/.images/Slika4.svg)


### Pomicanje, rotacija i vektori

Pomicanje i rotacija kamere kontrolira se u klasi `Camera`. Ta klasa sadrži varijable `x` i `y`, koje predstavljaju poziciju kamere u koordinatnom sustavu. Osim toga, sadrži varijable `dirX` i `dirY`, koje su skalari jediničnog vektora smjera kamere $\overrightarrow{dir}=dirX\displaystyle{\vec{\imath}}+dirY\displaystyle{\vec{\jmath}}$.

Također, postoji varijabla $\overrightarrow{plane}$ koja se sastoji od `planeX` i `planeY`. Ovaj vektor definira širinu vidnog polja kamere.

Na slici (vidi Sliku 5) se može vidjeti vektor $\overrightarrow{dir}$ i vektor $\overrightarrow{plane}$, oba duljine 1 i međusobno okomita.

![Slika 5](https://github.com/GabrielZrilic/Raycaster/blob/master/.images/Slika5.png)

#### Pomak

Pomicanje je veoma jednostavno. Imamo koordinate `x` i `y`, skalar `movementValue` koji se množi sa smijerom, te dvije vrijednosti se zbroje i dobijemo novu koordinatu. Ako je nova koordinata validna postavi je kao trenutnu koordinatu. Ovako to izgleda u programu:
```java
public static void updateLocation() {
    if (movement == Movement.FORWARD)
        move(movementValue);
    else if (movement == Movement.BACK)
        move(-movementValue);
}

private static void move(double scalar) {
    double newX = x + dirX * scalar;        // Računanje nove koordinate x
    double newY = y + dirY * scalar;        // Računanje nove koordinate y

    // Provjera je li koordinata unutar mape
    if (newX > 0 && newX < Constants.gridWidth - 1 && !Constants.map[(int) newY][(int) newX])
        x = newX;
    if (newY > 0 && newY < Constants.gridHeight - 1 && !Constants.map[(int) newY][(int) newX])
        y = newY;
    }
```

#### Rotacija

Rotacija je malo kompliciranija nego pomicanje. Ono što želimo rotirati jest vektor $\overrightarrow{dir}$ i $\overrightarrow{plane}$ oko središta koordinatnog sustava. Pošto ti vektori počinju iz točke, trebamo izračunati poziciju samo za krajnju točku.

U početku imamo vektor kao na slici (Slika 6):

![Slika 6](https://github.com/GabrielZrilic/Raycaster/blob/master/.images/Slika6.png)

Taj vektor želimo rotirati za određeni kut (Slika 7).

![Slika 7](https://github.com/GabrielZrilic/Raycaster/blob/master/.images/Slika7.png)

Možemo vidjeti kako su koordinate krajnje točke zapravo vrijednosti `dirX` i `dirY` (Slika 8), a za rotirani vektor nove vrijednosti ćemo nazvati $dirX'$ i $dirY'$ (Slika 9).

![Slika 8](https://github.com/GabrielZrilic/Raycaster/blob/master/.images/Slika8.png)
![Slika 9](https://github.com/GabrielZrilic/Raycaster/blob/master/.images/Slika9.png)

U idućim pojašnjenjima za duljinu vektora uzimamo $d$, jer se objašnjava kako se inače rotira vektor bilo koje duljine.

Kut između x osi i hipotenuze $d$ je $\theta$, i zbog toga znamo kako je:

```math
\cos\theta=\frac{dirX}{d}
```
```math
\sin\theta=\frac{dirY}{d}
```
<br></br>

```math
d\cos\theta=dirX
```
```math
d\sin\theta=dirY
```

Kut između vektora $\overrightarrow{dir}$ i $\overrightarrow{dir'}$ ćemo izraziti kao $\phi$. Zbog zoga znamo kako je kut između osi x i $\overrightarrow{dir'}$ jednak $\theta+\phi$. Kako bismo izračunali $dirX'$ i $dirY'$ slijedi:

```math
\cos(\theta+\phi)=\frac{dirX'}{d}
```
```math
\sin(\theta+\phi)=\frac{dirY'}{d}
```
<br></br>

```math
dirX'=d\cos(\theta+\phi)
```
```math
dirY'=d\sin(\theta+\phi)
```

Koristeći trigonometrijske indetitete rastavljamo na:

```math
dirX'=d(\cos\theta\cos\phi - \sin\theta\sin\phi)
```
```math
dirY'=d(\sin\theta\cos\phi + \cos\theta\sin\phi)
```
<br></br>

```math
dirX'=d\cos\theta\cos\phi - d\sin\theta\sin\phi
```
```math
dirY'=d\sin\theta\cos\phi + d\cos\theta\sin\phi
```

Koristeći $d\cos\theta$ i $d\sin\theta$ pojednostavljujemo na:

```math
dirX'=dirX\cos\phi-dirY\sin\phi
```
```math
dirY'=dirY\cos\phi+dirX\sin\phi
```

Taj izraz možemo prikazati matricom:

$$
\begin{bmatrix}dirX' \\\ dirY'\end{bmatrix} = 
\begin{bmatrix} \cos\phi & -\sin\phi \\\ \sin\phi & \cos\phi \end{bmatrix}
\begin{bmatrix}dirX \\\ dirY\end{bmatrix}
$$
<br></br>

![Slika 10](https://github.com/GabrielZrilic/Raycaster/blob/master/.images/Slika10.png)

U kodu je implementirano na ovaj način:
```java
public static void updateRotation() {
    if (rotation == Rotation.RIGHT)
        rotate(rotationValue);
    else if (rotation == Rotation.LEFT)
        rotate(-rotationValue);
}

private static void rotate(double phi) {
    // Spremi početne vrijednosti
    double initDirX = dirX, initDirY = dirY;
    double initPlaneX = planeX, initPlaneY = planeY;

    // Izračunaj kosinus i sinus vrijednost kuta
    double cos_phi = Math.cos(phi), sin_phi = Math.sin(phi);


    // Postavi nove vrijednosti
    dirX = initDirX * cos_phi - initDirY * sin_phi;
    dirY = initDirX * sin_phi + initDirY * cos_phi;

    planeX = initPlaneX * cos_phi - initPlaneY * sin_phi;
    planeY = initPlaneX * sin_phi + initPlaneY * cos_phi;
}
```

### Raycasting

Raycasting tehnika *ispaljuje* zrake, kada zraka naiđe na prepreku izračuna se udaljenost od početne točke do prepreke. Koristeći tu udaljenost i $\overrightarrow{plane}$ izračunamo okomucu na $\overrightarrow{plane}$ od točke kolizije s preprekom.

Ali jedna zraka ne može prikazati cijelu sliku. Zbog toga trebamo više zraka koje idu u različite smjerove. Trebamo onoliko vektora (zraka) koliko je širok ekran.

#### Stvaranje zrake

Prije nego što izračunamo odaljenost od kamere do objekta trebamo stvoriti zraku, točnije samo njezin smjer. To ćemo učiniti jediničnim vektorom. Za računanje toga porteban nam je $\overrightarrow{dir}$ i $\overrightarrow{plane}$.

#### Prikaz zrake
todo

## Zaključak
## Literatura
- [Java (programski jezik)](https://hr.wikipedia.org/wiki/Java_(programski_jezik))
- [Swing (Java)](https://en.wikipedia.org/wiki/Swing_(Java))
- [Wikipedia - Ray casting](https://en.wikipedia.org/wiki/Ray_casting)
- [Lode's Computer Graphics Tutorial](https://lodev.org/cgtutor/raycasting.html)
- [Ray casting fully explained. Pseudo 3D game](https://www.youtube.com/watch?v=g8p7nAbDz6Y)