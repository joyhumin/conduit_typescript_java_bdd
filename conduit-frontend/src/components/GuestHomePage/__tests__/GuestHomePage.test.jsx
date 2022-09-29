import React from "react";
import { create } from "react-test-renderer";
import { Box } from "@mui/material";
import { GuestHomePage } from "../GuestHomePage";

jest.mock("@mui/material", () => mockAllReactComponents(jest.requireActual("@mui/material")));

describe("GuestHomePage", () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  test("renders without error", () => {
    // Given
    const content = chance.string();

    // When
    const actual = create(<GuestHomePage content={content} />).root.findByType(Box);

    // Then
    expect(actual.props.children).toEqual(content);
  });
});
