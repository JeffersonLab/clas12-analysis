# clas12-analysis
This goal of this library is to provide an object-oriented library to access reconstructed banks for CLAS12 data analysis.
This library is currently under development, documentation and analysis examples will be added soon.

<!-- # Requirements 
* Java 11 [Installation guide](https://docs.oracle.com/en/java/javase/11/install/index.html)
* Maven [Install guide](https://maven.apache.org/users/index.html) -->

# Quick Start
* Requirements 
  * Java 11 or Greater [Installation guide for Java 11](https://docs.oracle.com/en/java/javase/11/install/index.html)
  * Maven [Installation and setup guide](https://maven.apache.org/users/index.html)
* Suggested IDE
  * IntelliJ [How to install and set up IntelliJ](https://www.jetbrains.com/help/idea/installation-guide.html)

### Release Option
 
If you just want to install the release jar rather than clone the repo:
* Click on `Releases`
* Download the most recent release jar of the clas12 analysis.
* Open an IDE
* Create a new project or use an existing project
* Then under project structure, go to the libraries section.
![AddingtoLibraryJarDepend](image_folder/AddingtoLibraryJarDepend.png)
* click the `+` and go to location of the downloaded jar release. 
* Then click `ok` to the following prompts
* Finally click `Apply`
* The jar should now be visible in the external libraries
![ExternalLibAdded](image_folder/ExternalLibAdded.png)


### Install

Clone the clas12-analysis repository:

    git clone https://github.com/JeffersonLab/clas12-analysis.git

### Setup
    
Once you clone the project to your computer, open an IDE (We are assuming you are using IntelliJ):
* Inside IntelliJ, click `Open` and then go to the location of where you cloned the project.
* Click on the project and press `OK` as shown in the image below.
![Open](image_folder/Open.png)

If a prompt appears about the project as maven, click **Trust**.

Then **Right click** on the **src** folder in clas12-analysis >> go to **Mark Directory as** >> click **Source Root**.
![SourceRoot](image_folder/SourceRoot.png)

After that, **right click** on the **pom.xml** file >> go to **Maven** >> then click **Reload Project**.
![pomReload](image_folder/pomReload.png)
Now you can run the files under the examples folder and make sure to have your data file with you.
