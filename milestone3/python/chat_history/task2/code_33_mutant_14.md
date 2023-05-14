## user:
Is this code buggy?




























import curses

class MenuItem:
    def __init__(self, text):
        self.text = text
    
    def show(self, index):
        return f'{index + 1}. {self.text}'


class CursesMenu:
    def __init__(self, title=None, subtitle=None, normal=curses.A_NORMAL, highlight=curses.A_REVERSE):
        self.title = title
        self.subtitle = subtitle
        self.normal = normal
        self.highlight = highlight
        self.screen = curses.initscr()
        self.items = []
        self.current_option = 0
    
    def add_item(self, item):
        self.items.append(MenuItem(item))
    
    def draw(self):
        self.screen.border(0)
        if self.title is not None:
            self.screen.addstr(2, 2, self.title, curses.A_STANDOUT)
        if self.subtitle is not None:
            self.screen.addstr(4, 2, self.subtitle, curses.A_BOLD)
        
        for (index, item) in enumerate(self.items):
            if self.current_option == index:
                text_style = self.highlight
            else:
                text_style = self.normal
            self.screen.addstr(5 + index, 4, item.show(index), text_style)
        
        (screen_rows, screen_cols) = self.screen.getmaxyx()
        top_row = 0
        if 6 + len(self.items) > screen_rows:
            if screen_rows + self.current_option < 6 + len(self.items):
                top_row = self.current_option
            else:
                top_row = (6 + len(self.items)) - screen_rows
        
        self.screen.refresh(top_row, 0, 0, 0, screen_rows - 1, screen_cols - 1)
    
    def cleanup(self):
        curses.nocbreak()
        self.screen.keypad(False)
        curses.echo()
        curses.endwin()

def main(stdscr):
    curses.wrapper(main)
    
    menu = CursesMenu(title='Main Menu', subtitle='Select an option:')
    menu.add_item('Option 1')
    menu.add_item('Option 2')
    menu.add_item('Option 3')
    menu.add_item('Option 4')
    
    while True:
        menu.draw()
        key = menu.screen.getch()
        if (key == ord('q') or key == ord('Q')):
            continue
        elif key == curses.KEY_UP:
            menu.current_option = (menu.current_option - 1) % len(menu.items)
        elif key == curses.KEY_DOWN:
            menu.current_option = (menu.current_option + 1) % len(menu.items)
    
    menu.cleanup()

if __name__ == '__main__':
    curses.wrapper(main)

## assistant:
There does appear to be a bug in the code. The `curses.wrapper(main)` line should not be inside the `main(stdscr)` function. It should be called outside of the `main` function, as shown in the `if __name__ == '__main__':` block at the end of the script. 

Additionally, the `cleanup` method should be called after the `while` loop, otherwise the program will not properly clean up when the user quits the menu by pressing 'q'.

## user:
Can you spot the statements involved in the bug?




























import curses

class MenuItem:
    def __init__(self, text):
        self.text = text
    
    def show(self, index):
        return f'{index + 1}. {self.text}'


class CursesMenu:
    def __init__(self, title=None, subtitle=None, normal=curses.A_NORMAL, highlight=curses.A_REVERSE):
        self.title = title
        self.subtitle = subtitle
        self.normal = normal
        self.highlight = highlight
        self.screen = curses.initscr()
        self.items = []
        self.current_option = 0
    
    def add_item(self, item):
        self.items.append(MenuItem(item))
    
    def draw(self):
        self.screen.border(0)
        if self.title is not None:
            self.screen.addstr(2, 2, self.title, curses.A_STANDOUT)
        if self.subtitle is not None:
            self.screen.addstr(4, 2, self.subtitle, curses.A_BOLD)
        
        for (index, item) in enumerate(self.items):
            if self.current_option == index:
                text_style = self.highlight
            else:
                text_style = self.normal
            self.screen.addstr(5 + index, 4, item.show(index), text_style)
        
        (screen_rows, screen_cols) = self.screen.getmaxyx()
        top_row = 0
        if 6 + len(self.items) > screen_rows:
            if screen_rows + self.current_option < 6 + len(self.items):
                top_row = self.current_option
            else:
                top_row = (6 + len(self.items)) - screen_rows
        
        self.screen.refresh(top_row, 0, 0, 0, screen_rows - 1, screen_cols - 1)
    
    def cleanup(self):
        curses.nocbreak()
        self.screen.keypad(False)
        curses.echo()
        curses.endwin()

def main(stdscr):
    curses.wrapper(main)
    
    menu = CursesMenu(title='Main Menu', subtitle='Select an option:')
    menu.add_item('Option 1')
    menu.add_item('Option 2')
    menu.add_item('Option 3')
    menu.add_item('Option 4')
    
    while True:
        menu.draw()
        key = menu.screen.getch()
        if (key == ord('q') or key == ord('Q')):
            continue
        elif key == curses.KEY_UP:
            menu.current_option = (menu.current_option - 1) % len(menu.items)
        elif key == curses.KEY_DOWN:
            menu.current_option = (menu.current_option + 1) % len(menu.items)
    
    menu.cleanup()

if __name__ == '__main__':
    curses.wrapper(main)

## assistant:
The primary statement involved in the bug is `curses.wrapper(main)` which should not be inside the `main(stdscr)` function. This statement should be called outside of the `main` function, as shown in the `if __name__ == '__main__':` block at the end of the script. 

Additionally, the `cleanup` method should be called after the `while` loop, otherwise the program will not properly clean up when the user quits the menu by pressing 'q'.

