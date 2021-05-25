"""
This little script is designed to generate a dictionary based on an existing one. Internally it uses a translator
with which it is able to translate the values of the dictionary properties. The translator as well as the output
language can be configured from the parameters.

To get more information about the parameters and commands you can use the command "--help".
"""

import os
import sys
from platform import system, version


NAME = "Dictionary generator"
VERSION = "1.0.0"


def missing_argument(arg):
    print(f'The "{arg}" parameter was not found')
    sys.exit(1)


class ArgumentProcessor:
    def __init__(self, args: dict):
        super(ArgumentProcessor, self).__init__()
        self.__args = args
        self.__source: str = ""
        self.__target: str = ""
        self.__translator = None
        self.__file: str = ""
        self.__output: str = ""

    def get_translator(self, translator) -> object:
        if translator == "gt":
            from deep_translator import GoogleTranslator

            return GoogleTranslator(source=self.__source, target=self.__target)
        elif translator == "lt":
            from deep_translator import LingueeTranslator

            return LingueeTranslator(source=self.__source, target=self.__target)
        elif translator == "mmt":
            from deep_translator import MyMemoryTranslator

            return MyMemoryTranslator(source=self.__source, target=self.__target)
        elif translator == "pt":
            from deep_translator import PonsTranslator

            return PonsTranslator(source=self.__source, target=self.__target)

    def prepare(self):
        if "src" in self.__args:
            self.__source = self.__args["src"]
        else:
            missing_argument("src")
        if "tg" in self.__args:
            self.__target = self.__args["tg"]
        else:
            missing_argument("tg")
        if "file" in self.__args:
            self.__file = self.__args["file"]
        else:
            missing_argument("file")
        if "tr" in self.__args:
            self.__translator = self.get_translator(self.__args["tr"])
        else:
            self.__translator = self.get_translator("mmt")
        self.__output = os.path.join(os.getcwd())

    def dump(self):
        output_name = (
            self.__output
            + "\\"
            + self.__target.upper()
            + "_"
            + self.__target.lower()
            + ".txt"
        )
        source_file = open(self.__file, "r", encoding="utf-8")
        target_file = open(output_name, "w", encoding="utf8")
        for line in source_file:
            if line[0] == "#" or line == "\n":
                continue
            else:
                key, value = line.split("=")
                translated = self.__translator.translate(value)
                target_file.write(f"{key}={translated}\n")
        source_file.close()
        target_file.close()

    def execute(self):
        self.prepare()
        self.dump()


def show_help():
    print(f"{NAME} {VERSION} ({system()} {version()})")
    print("Usage:")
    print(f"{' ' * 8}gen [arguments<key=value>]")
    print("The arguments are:")
    print(f"{' ' * 8}src        input language")
    print(f"{' ' * 8}tg         output language")
    print(f"{' ' * 8}file       entry template")
    print(f"{' ' * 8}tr         used translator")
    print("The commands are:")
    print(
        f"{' ' * 8}-h, --help                 shows a guide to all commands and arguments"
    )
    print(
        f"{' ' * 8}-v, --version              shows the name and version of the script"
    )
    print(
        f"{' ' * 8}-trs, --translators        displays the list of available translators"
    )


def show_translators():
    print(f"{' ' * 4}-> Google translator        <gt>")
    print(f"{' ' * 4}-> Linguee translator       <lt>")
    print(f"{' ' * 4}-> MyMemory translator      <mmt>")
    print(f"{' ' * 4}-> Pons translator          <pt>")


def main():
    console_arguments = sys.argv
    del console_arguments[0]
    if len(console_arguments) > 0:
        if len(console_arguments) == 1:
            argument = console_arguments[0]
            if argument == "--help" or argument == "-h":
                show_help()
            elif argument == "--version" or argument == "-v":
                print(f"{NAME} {VERSION} ({system()} {version()})")
            elif argument == "--translators" or argument == "-trs":
                print("Translators available:")
                show_translators()
        else:
            argument_set = {}
            for argument in console_arguments:
                if "=" in argument:
                    values = argument.split("=")
                    argument_set[values[0]] = values[1]
            if len(argument_set) > 0:
                processor = ArgumentProcessor(argument_set)
                processor.execute()
            else:
                for unknown_argument in console_arguments:
                    print(f'"{unknown_argument}"', end=", ")
                print("are not recognized as valid arguments")
    else:
        print("No argument has been passed")
        show_help()


if __name__ == "__main__":
    main()
