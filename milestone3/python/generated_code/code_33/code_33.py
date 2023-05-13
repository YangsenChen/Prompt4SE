def draw(self):
        """
        Redraws the menu and refreshes the screen. Should be called whenever something changes that needs to be redrawn.
        """

        self.screen.border(0)
        if self.title is not None:
            self.screen.addstr(2, 2, self.title, curses.A_STANDOUT)
        if self.subtitle is not None:
            self.screen.addstr(4, 2, self.subtitle, curses.A_BOLD)

        for index, item in enumerate(self.items):
            if self.current_option == index:
                text_style = self.highlight
            else:
                text_style = self.normal
            self.screen.addstr(5 + index, 4, item.show(index), text_style)

        screen_rows, screen_cols = CursesMenu.stdscr.getmaxyx()
        top_row = 0
        if 6 + len(self.items) > screen_rows:
            if screen_rows + self.current_option < 6 + len(self.items):
                top_row = self.current_option
            else:
                top_row = 6 + len(self.items) - screen_rows

        self.screen.refresh(top_row, 0, 0, 0, screen_rows - 1, screen_cols - 1)