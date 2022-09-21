/**
 * This is the default MUˆ theme object.
 * Theme UI Spec (see: https://mui.com/customization/default-theme/#main-content)
 * Override the theme
 * @see: https://mui.com/customization/theme-components/#global-style-overrides
 * @see: https://mui.com/customization/palette/#heading-adding-new-colors
 */

import { createTheme } from '@mui/material/styles';

const PRIMARY = "#5CB85C";
// const WHITE = "#ffffff"

const theme = createTheme({
  palette: {
    primary: {
      main: PRIMARY,
    },
    text: {
      primary: "#373a3c",
      secondary: PRIMARY,
    }
  }
});

export default theme;