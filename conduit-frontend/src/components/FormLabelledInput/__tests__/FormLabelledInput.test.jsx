import React from "react";
import { create } from "react-test-renderer";
import { Box } from "@mui/material";
import { FormLabelledInput } from "../FormLabelledInput";

jest.mock("@mui/material", () => mockAllReactComponents(jest.requireActual("@mui/material")));

describe("FormLabelledInput", () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  test("renders without error", () => {
    // Given
    const content = chance.string();

    // When
    const actual = create(<FormLabelledInput content={content} />).root.findByType(Box);

    // Then
    expect(actual.props.children).toEqual(content);
  });
});
