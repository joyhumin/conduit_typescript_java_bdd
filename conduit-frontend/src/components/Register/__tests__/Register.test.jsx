import React from "react";
import { create } from "react-test-renderer";
import { Box } from "@mui/material";
import { Register } from "../Register";

jest.mock("@mui/material", () => mockAllReactComponents(jest.requireActual("@mui/material")));

describe("Register", () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  test("renders without error", () => {
    // Given
    const content = chance.string();

    // When
    const actual = create(<Register content={content} />).root.findByType(Box);

    // Then
    expect(actual.props.children).toEqual(content);
  });
});
