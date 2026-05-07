import { FilterBuilder } from "@svar-ui/react-filter";
import "@svar-ui/react-filter/all.css";
import { Willow } from "@svar-ui/react-filter";
// import { Filemanager } from "@svar-ui/svelte-filemanager";
// import { getData, getDrive } from "./common/data";
// import { Editor } from "@svar-ui/react-editor";
// import "@svar-ui/react-editor/all.css";

const fields = [
  { id: "first_name", label: "First Name", type: "text" },
  { id: "age", label: "Age", type: "number" },
  { id: "start", label: "Start Date", type: "date", format: "%y-%m-%d" },
];

const options = {
  first_name: ["Alex", "Adam", "John", "Jane"],
  age: [17, 21, 35, 42],
  start: [new Date(2025, 4, 7), new Date(2025, 4, 10)],
};

export default function MyFiltering() {
  return (
    <Willow>
      {/* <Editor css="myBox" /> */}
      <FilterBuilder fields={fields} options={options} />
    </Willow>
  );
}
