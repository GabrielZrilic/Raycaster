# Raycaster - Završni rad

Raycasting je tehnika prikazivanja 2D prostora u *3D-u*.

## Sadržaj
- [Raycaster](#raycaster)
    - [Instalacija](#instalacija)
    - [Korištenje](#korištenje)
    - [Korištene tehnologije](#korištene-tehnologije)
    - [Struktura programa](#struktura-programa)
    - [Pomicanje, rotacija i vektori](#pomicanje-rotacija-i-vektori)
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

![Slika 1](https://github.com/GabrielZrilic/Raycaster/blob/master/.images/Slika1.png)

Klikanjem na gumbe, možete jednostavno dodavati ili uklanjati zidove na mapi (vidi Sliku 2). Sustav zidova je organiziran u tablici, što je ključno za ispravno funkcioniranje raycasting (DDA) algoritma.

![Slika 2](https://github.com/GabrielZrilic/Raycaster/blob/master/.images/Slika2.png)

Kada završite s postavljanjem zidova, jednostavno pritisnite gumb `Create`. Prozor za uređivanje zidova će se zatvoriti, a umjesto njega će se pojaviti novi prozor s 3D prikazom mape koju ste prethodno stvorili. Sada možete kontrolirati kameru koristeći tipke sa strelicama na tipkovnici.

![Slika 3](https://github.com/GabrielZrilic/Raycaster/blob/master/.images/Slika3.png)

Ovaj program pruža zanimljivu vizualizaciju rada raycasting algoritma i omogućuje vam da eksperimentirate s različitim mapama kako biste bolje razumjeli njegovo funkcioniranje.

### Korištene tehnologije

Ovaj projekt koristi nekoliko ključnih tehnologija kako bi omogućio raycasting simulaciju:

- **Java**: Osnovni programski jezik u kojem je napisana aplikacija. Java omogućuje platformsku neovisnost i lakoću razvoja.

- **Visual Studio Code (VSCode)**: Uređivač koda koji je korišten za razvoj Java aplikacije. VSCode je popularan alat među programerima zbog svoje prilagodljivosti i ekstenzibilnosti.

- **GitHub**: Za verzioniranje koda i suradnju na projektu korišten je GitHub repozitorij. Ovo omogućuje timski rad, praćenje promjena i upravljanje projektom.

- **FlatLaf**: Tema za Java Swing koja pruža moderno i privlačno korisničko sučelje. FlatLaf je odabran kako bi se poboljšala estetika aplikacije i omogućila bolja interakcija s korisnicima.

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

![Slika 10](https://github.com/GabrielZrilic/Raycaster/blob/master/.images/Slika10.png)

### Raycasting


## Zaključak
## Literatura
- [Wikipedia - Ray casting](https://en.wikipedia.org/wiki/Ray_casting)
- [Lode's Computer Graphics Tutorial](https://lodev.org/cgtutor/raycasting.html)
- [Ray casting fully explained. Pseudo 3D game](https://www.youtube.com/watch?v=g8p7nAbDz6Y)
## Prilozi