import React from "react";

interface Props {
  type: string;
  name: string;
  label: string;
  placeholder?: string;
}

export const FormLabelledInput: React.FC<Props> = ({ type, name, label }) => {
  return (
    <p>
      <label htmlFor={name}>{label}: </label>
      <input type={type} id={name} name={name} required={true} />
    </p>
  );
};
