import React from "react";
import { create } from "react-test-renderer";
import { AppBar, Toolbar, Typography } from "@mui/material";
import { AppHeader } from "../AppHeader";

jest.mock("@mui/material", () => mockAllReactComponents(jest.requireActual("@mui/material")));

describe("AppHeader", () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  test("renders without error", () => {
    // Given

    // When
    const actual = create(<AppHeader />).root;

    // Then
    expect(actual.findByType(AppBar)).toBeDefined();
    expect(actual.findByType(Toolbar)).toBeDefined();
    expect(actual.findByType(Typography)).toBeDefined();
  });
});
