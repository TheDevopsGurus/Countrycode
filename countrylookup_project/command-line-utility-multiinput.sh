echo "Enter country codes"; 
read INPUT
LIST=`echo $INPUT| tr "," " "`
echo $LIST
for each in $LIST

do
       
INPUT=${each^^}
 
 java -jar /c/Users/user/Desktop/project/countrylookup_project/CountryLookup/target/lookup.jar --countryCode=$INPUT


done