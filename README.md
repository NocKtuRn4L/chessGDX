# ChessGDK

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

## Proof Of Concept

## Software Architecture

- **Programming Language:** Java (JDK 21)
- **Framework:** libGDK (Java Game API)
- **Operating System:** Cross-platform (Windows, Mac, Linux)

## External Dependencies

The project uses the Stockfish engine for AI moves, which is integrated as an external executable. The path to the Stockfish binary is provided in the `ChessGame` class. Stockfish runs as a separate process, and the game communicates with it via input/output streams. In order to run this program you will need to have Stockfish installed on the local machine

## Instructions to Run:
1. **Clone git repo to desired folder**
2. **Install IntelliJ IDEA or VSCode:**
   - Open the project in IntelliJ IDEA. The IDE will automatically recognize the Gradle configuration and resolve any dependencies.
   - If you use VSCode install the Java extension pack
   - Use the gradle interface to build the project

3. **Stockfish Path:**
   - Update the path to the Stockfish executable in the `ChessGame` class, ensuring that it points to the correct location on the user's system.

4. **Run the Project:**
   - The class to run is `Lwjgl3Launcher.java` in `lwjgl3/src/main/java/org/chessGDK/lwjgl3` which can be executed by right-clicking on it in IntelliJ and selecting "Run". This will launch the chess game window and perform an initial demonstration of the game's functionality.


## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.
