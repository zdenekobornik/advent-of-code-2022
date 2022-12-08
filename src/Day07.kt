import kotlin.math.min

sealed class Data {
    data class Directory(
        val name: String,
        val parent: Directory? = null,
        var children: MutableList<Data> = mutableListOf()
    ) : Data()

    data class File(val size: Long, val name: String) : Data()
}

data class Drive(
    var currentDirectory: Data.Directory = Data.Directory("/")
) {
    fun jumpToParentDir() {
        val currentParent = currentDirectory.parent

        if (currentParent != null) {
            currentDirectory = currentParent
        }
    }

    fun openDir(dir: String) {
        val foundDirectory = currentDirectory.children.filterIsInstance<Data.Directory>().find { it.name == dir }

        if (foundDirectory != null) {
            currentDirectory = foundDirectory
        }
    }

    fun addFile(file: Data.File) {
        currentDirectory.children.add(file)
    }

    fun createDirectory(name: String) {
        val directory = Data.Directory(name, currentDirectory, mutableListOf())
        currentDirectory.children.add(directory)
    }
}

fun main() {

    fun Drive.processInput(input: List<String>) {
        for (line in input) {
            if (line.startsWith('$')) {
                if (line.startsWith("$ cd")) {
                    val (_, _, dir) = line.split(' ')

                    if (dir == "..") {
                        jumpToParentDir()
                    } else if (dir == "/") {
                        continue
                    } else {
                        openDir(dir)
                    }
                }
            } else {
                val (size, name) = line.split(' ')

                if (size == "dir") {
                    createDirectory(name)
                } else {
                    addFile(Data.File(size.toLong(), name))
                }
            }
        }
    }

    fun part1(input: List<String>): Long {
        val drive = Drive()
        val parentFolder = drive.currentDirectory

        drive.processInput(input)

        var count = 0L

        fun computeTotalBytes(directory: Data.Directory): Long {
            val size = directory.children.sumOf { data ->
                when (data) {
                    is Data.Directory -> computeTotalBytes(data)
                    is Data.File -> data.size
                }
            }

            if (size <= 100000) {
                count += size
            }

            return size
        }

        computeTotalBytes(parentFolder)

        return count
    }

    fun part2(input: List<String>): Long {
        val drive = Drive()
        val parentFolder = drive.currentDirectory

        drive.processInput(input)

        fun computeTotalBytes(directory: Data.Directory): Long {
            return directory.children.sumOf { data ->
                when (data) {
                    is Data.Directory -> computeTotalBytes(data)
                    is Data.File -> data.size
                }
            }
        }

        val totalSize = computeTotalBytes(parentFolder)
        val minDeleteSize = 30000000 - (70000000 - totalSize)

        var currentDeleteSize = Long.MAX_VALUE

        fun detectDirectoryForDeletion(directory: Data.Directory): Long {
            val size = directory.children.sumOf { data ->
                when (data) {
                    is Data.Directory -> detectDirectoryForDeletion(data)
                    is Data.File -> data.size
                }
            }

            if (size >= minDeleteSize) {
                currentDeleteSize = min(currentDeleteSize, size)
            }

            return size
        }

        detectDirectoryForDeletion(parentFolder)

        return currentDeleteSize
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    checkResult(part1(testInput), 95437)
    checkResult(part2(testInput), 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
