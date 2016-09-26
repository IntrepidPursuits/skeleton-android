#!/usr/bin/env bash

OPTIND=1         # Reset in case getopts has been used previously in the shell.

# Initialize our own variables:
oldPackageName="io.intrepid.skeleton"
oldApplicationCapitalizedName="Skeleton"
oldDirectoryName="io/intrepid/skeleton"
oldDirectoryPrefix="io/intrepid/"

packageRegex="([a-z]+\.){1,3}([a-z]+)$"

downloadDirectory=./
newPackageName=$oldPackageName

while getopts "d:p:" opt; do
    case "$opt" in
    d)  downloadDirectory=$OPTARG
        ;;
    p)  newPackageName=$OPTARG
        ;;
    esac
done
shift $((OPTIND-1))
[ "$1" = "--" ] && shift

if [[ $newPackageName =~ $packageRegex ]]; then
    newApplicationName=${BASH_REMATCH[2]}
    newApplicationCapitalizedName="$(tr '[:lower:]' '[:upper:]' <<< ${newApplicationName:0:1})${newApplicationName:1}"
    newDirectoryName=${newPackageName//\./\/}
else
    echo "The package name should be in the for of x.y.z";
    exit
fi

git clone https://github.com/IntrepidPursuits/AndroidSkeleton.git $downloadDirectory/$newApplicationCapitalizedName

cd $downloadDirectory/$newApplicationCapitalizedName
rm necromancer.sh

if [ "$newPackageName" != "$oldPackageName" ]; then
    cd app

    mv src/main/java/io/intrepid/skeleton/SkeletonApplication.java src/main/java/io/intrepid/skeleton/${newApplicationCapitalizedName}Application.java

    find . -type f \( ! -iname "*.png" \) -exec sed -i '' "s/$oldPackageName/$newPackageName/g" {} \;
    find . -type f \( ! -iname "*.png" \) -exec sed -i '' "s/$oldApplicationCapitalizedName/$newApplicationCapitalizedName/g" {} \;

    mkdir temp
    declare -a srcDirs=("androidTest" "main" "test")
    for i in "${srcDirs[@]}"
    do
	    mv src/$i/java/io/intrepid/skeleton/* temp/
	    rm -r src/$i/java/io/
	    mkdir -p src/$i/java/$newDirectoryName
	    mv temp/* src/$i/java/$newDirectoryName/
    done
    rmdir temp
fi
