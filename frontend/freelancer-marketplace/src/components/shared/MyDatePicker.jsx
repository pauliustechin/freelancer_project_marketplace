import DatePicker from "react-datepicker";
import { FaRegCalendarAlt } from "react-icons/fa";
import "react-datepicker/dist/react-datepicker.css";

const MyDatePicker = ({ onChange, value, placeholder, minDate, disabled }) => {


  return (
    <div className="relative w-full">
      <DatePicker
        selected={value || null}
        onChange={onChange}
        minDate={minDate}
        disabled={disabled}
        dateFormat="MM/dd/yyyy"
        className="w-full bg-slate-800 text-white p-2 pl-10 border border-gray-600 rounded-xl focus:outline-none"
        placeholderText={placeholder || "Select a date"}
        wrapperClassName="w-full"
      />
      <FaRegCalendarAlt className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 pointer-events-none" />
    </div>
  );
};

export default MyDatePicker;