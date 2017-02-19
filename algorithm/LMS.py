# a O(nlogn) algorithm is solve the longest monotonically increasing subsequence
from random import *
import math
import time

example = []

for i in range(10000):
    example.append(-100 + 200*random())

# an O(nlogn) DP algorithm to solve the longest monotonically increasing subsequence
# where optimal substructure is the set of subsequences of length from 1 to i that has the smallest rightmost element in the array so far
def find_largest_left(array, a, min, max):
    if min==max:
        if (a<array[min]):
            return -1
        else: return min
    j = min + (max-min)//2
    if a<array[j]:
        return find_largest_left(array, a, min, j)
    elif array[j]<=a and a<array[j+1]:
        return j
    else: return find_largest_left(array, a, j+1, max)

print("an O(nlogn) algorithm")
start = time.time()
B = []
A = example
l = 0
B.append(A[0])
l+=1

for i in range(1,len(A)):
    j = find_largest_left(B, A[i], 0, l-1)
    if j == l-1:
        l+=1
        B.append(A[i])
    elif B[j+1]>A[i]:
        B[j+1] = A[i]
#print(B)
print(l)
end = time.time();
print("time elapsed =", end -start)

# an O(n^2) DP algorithm to solve the LMS, where optimal substructure is the longest subsequence that ENDS at index i
print("an O(n^2) algorithm")
start = time.time()
B = [] #the length of the the longest subsequence that ends at i
C = [] #the index of the previous element that belongs to the longest subsequence that ends at the current index
A = example
sub_max = 1
max_index = 0
sub_max_index = -1
B.append(1)
C.append(-1)
for i in range(1,len(A)):
    for j in range(0, i):
        if A[i]>=A[j] and B[j]+1>sub_max:
            sub_max = B[j]+1
            sub_max_index = j
    B.append(sub_max)
    C.append(sub_max_index)
    sub_max = 1
    sub_max_index = -1
    if i != -1 and B[max_index] < B[i]:
        max_index = i

#D = []
print(B[max_index])
#while (max_index != -1):
#    D = [A[max_index]]+D
#    max_index = C[max_index]
#print(D)
#print(B)
end = time.time()
print("time elapsed =", end - start)

