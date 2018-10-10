import math
import numpy
import matplotlib.pyplot as plt

# Zadanie pierwsze
k=1240-math.sqrt(7)
m=4467
l=2j
d=k+m
c=d+l
print("%.6f"%d)

# Obliczanie Walca
r=17
h=33
print(math.pi*r*r*h)

# Wyrazenie
# x=input('Podaj x: ')
# t=input('Podaj t: ')
# r=input('Podaj r: ')
# B=(x+r)/(3.3456+r*math.sin(2*x))*math.pow(x,t+r)
# print(B)

# Macierz
a=math.sqrt(2)
M=numpy.array([[a,1,-a],[0,1,1],[-a,a,1]])
Minv = numpy.linalg.inv(M)
Mdet = numpy.linalg.det(M)
Mt = numpy.transpose(M)
print(Minv)
print(Mdet)
print(Mt)

# Zad 6
print(M[0][0])
print(M[2][2])
print(M[2][1])
w1 = M[:,2]
w2 = M[1,:]
print w1
print w2

# pierwiastki (Zad7)
p = [1,-7,3,43,-28,-60]
print numpy.roots(p)

# ciag
print numpy.arange(0,15,1.0/3)
print numpy.linspace(0,15,51)

# funkcja
def f(x):
    return math.pow(x,3)-3*x

def plotmaker(start,stop):
    x = numpy.linspace(start,stop)
    y = []
    for i in x:
        y.append(f(i))

    plt.plot(x,y)
    plt.title('wykres f(x)')
    plt.xlabel('x')
    plt.ylabel('y')
    plt.legend('wykres funkcji')
    plt.show()

plotmaker(-1,1)
plotmaker(-5,5)
plotmaker(0,5)

# zad 10 cieplo

def Q(m,v):
    return m*v*v/2

def kcal(J):
    return J/4184

print Q(2.5,50)

v=numpy.linspace(200,0)
plt.plot(v,[Q(m,i) for i in v])
plt.show
