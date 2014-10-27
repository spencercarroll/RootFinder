javac -d . Core/*.java
jar cmf Manifest.txt rootFinder.jar RootFinder/Core/*
rm -rf RootFinder
