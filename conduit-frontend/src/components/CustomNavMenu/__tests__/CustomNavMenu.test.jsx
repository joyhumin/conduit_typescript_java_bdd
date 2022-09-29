import React from "react";
import { create } from "react-test-renderer";
import { Box } from "@mui/material";
import { CustomNavMenu } from "../CustomNavMenu";

jest.mock("@mui/material", () => mockAllReactComponents(jest.requireActual("@mui/material")));

describe("CustomNavMenu", () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  test("renders without error", () => {
    // Given
    const content = chance.string();

    // When
    const actual = create(<CustomNavMenu content={content} />).root.findByType(Box);

    // Then
    expect(actual.props.children).toEqual(content);
  });
});
