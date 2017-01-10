comment = """
I wrote this so that I could generate random maps for battlecode.
Java fileIO is a pain.  Java multiline strings are a pain.  You get the idea
You're welcome to use it, but you're going to need scipy to run it.
@author Tom Temple
"""

from numpy import *
from scipy import *


filename = None
width = None
height = None

filename = filename or "smallnonempty.xml"

file = open(filename,'w')
width = width or 30
height = height or 30

header = ["<map width=\""+str(width)+"\" height=\""+str(height)+"\">\n",
          """<game seed="2323" rounds="3500"/>
	<symbols>
		<symbol character=" " type="TERRAIN" terrain="LAND"/>
		<symbol character="G" type="TERRAIN" terrain="LAND"/>
		<symbol character="#" type="TERRAIN" terrain="WATER"/>

		<symbol character="A" type="ARCHON" team="A"/>
		<symbol character="B" type="ARCHON" team="B"/>

		<symbol character="T" type="TOWER" team="NEUTRAL"/>
		<symbol character="C" type="TOWER" team="A"/>
		<symbol character="D" type="TOWER" team="B"/>
	</symbols>

	<!-- Map data -->
	<data><![CDATA[
"""]

middle = ["""\t]]></data>
	<height><![CDATA[
"""]

footer = ["""\t]]></height>
</map>"""]

maptiles = rand(height,width)
smoothrounds = 5
for r in range(smoothrounds):
    maptilesnew = copy(maptiles)
    for i in arange(1,height-1):
        for j in arange(1,width-1):
            maptilesnew[i,j] += maptiles[i-1,j]+maptiles[i+1,j]+\
                maptiles[i,j-1]+maptiles[i,j+1]
    maptiles = copy(maptilesnew)
sorted = sort(maptiles.flatten())
fraction_full = 1-.25
threshold = sorted[floor(height*width*fraction_full)]
map = ['#'*width]

occ = where(maptiles<threshold,' ','#')
#for i in arange(1,height-1,9):
#    occ[i][arange(1,width-1,9)] = 'A'
occ[height-2][width-2] = 'B'
occ[1][1] = 'A'

for i in arange(1,height-1):
    line = '#'
    for j in arange(1,width-1):
        line += occ[i][j]
    line += '#'
    map += [str(line)]
map += ['#'*width]
map = ['\t\t'+s+'\n' for s in map]
heighttiles = floor(5*rand(height,width))
heights = []
for i in arange(height):
    heights += [''.join([str(int(h)) for h in heighttiles[i]])]
heights = ['\t\t'+s + '\n' for s in heights]

file.writelines(header+map+middle+heights+footer)
file.close()
