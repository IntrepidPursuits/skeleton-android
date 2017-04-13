#!/usr/bin/env bash

OPTIND=1         # Reset in case getopts has been used previously in the shell.

# Initialize our own variables:
oldPackageName="io.intrepid.skeleton"
oldApplicationCapitalizedName="Skeleton"
oldDirectoryName="io/intrepid/skeleton"
oldDirectoryPrefix="io/intrepid/"
cleanHistory=false

# We do not check for Java keywords... We should... but we don't, we also enforce lowercase names. http://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html
packageRegex='^([a-z_][a-z_0-9]*\.)*([a-z_][a-z_0-9]+)$'

downloadDirectory=./
newPackageName=$oldPackageName

while getopts "d:p:c" opt; do
    case "$opt" in
    d)  downloadDirectory=$OPTARG
        ;;
    p)  newPackageName=$OPTARG
        ;;
    c)  cleanHistory=true
        ;;
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
    echo "The package name should be in the form of x.y.z. Package parts cannot start with an integer or end with a full stop ('.').";
    exit
fi

git clone https://github.com/IntrepidPursuits/AndroidSkeleton.git $downloadDirectory/$newApplicationCapitalizedName

cd $downloadDirectory/$newApplicationCapitalizedName
rm necromancer.sh
git remote remove origin

if [ "$cleanHistory" = true ] ; then
  rm -rf .git
  git init
fi

if [ "$newPackageName" != "$oldPackageName" ]; then
    cd app

    mv src/main/java/io/intrepid/skeleton/SkeletonApplication.java src/main/java/io/intrepid/skeleton/${newApplicationCapitalizedName}Application.java

    # Not all Macs have LANG set. If they dont and its not C SED can error with 'sed: RE error: illegal byte sequence'.
    #    Because -exec subshells... we need to export our environment... So store the one we came in with... Export 'C' then reset.
    OLD_LANG=$LANG
    export LANG=C
    find . -type f \( ! -iname "*.png" \) -exec sed -i '' "s/$oldPackageName/$newPackageName/g" {} \;
    find . -type f \( ! -iname "*.png" \) -exec sed -i '' "s/$oldApplicationCapitalizedName/$newApplicationCapitalizedName/g" {} \;
    export LANG=$OLD_LANG

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

git add --all
git commit -m "Initial import from Skeleton"
git add --force .gitignore
