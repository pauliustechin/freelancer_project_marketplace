import { Tooltip } from "@mui/material";
import { useEffect, useState } from "react";
import { FiArrowDown, FiArrowUp, FiRefreshCw, FiSearch } from "react-icons/fi";
import { useNavigate } from "react-router";
import MyDatePicker from "../../../components/shared/MyDatePicker";

const ProjectFilter = ({ setPage }) => {
  const navigate = useNavigate();

  const [sortOrder, setSortOrder] = useState("");
  const [searchTerm, setSearchTerm] = useState("");
  const [startDate, setStartDate] = useState(new Date());
  const [pageSize, setPageSize] = useState(null);

  useEffect(() => {
    const params = new URLSearchParams();
    console.log("render filter");

    if (pageSize) {
      params.set("size", pageSize);
    }

    if (sortOrder) {
      params.set("sort", `createdAt,${sortOrder}`);
    }

    if (startDate > new Date()) {
      params.set("projectStart", startDate.toISOString().split("T")[0]);
    }

    if (searchTerm) {
      const handler = setTimeout(() => {
        if (searchTerm) {
          params.set("projectName", searchTerm);
        } else {
          params.delete("projectName");
        }

        navigate(`?${params.toString()}`);
      }, 700);

      return () => {
        clearTimeout(handler);
      };
    } else {
      navigate(`?${params.toString()}`);
    }
  }, [searchTerm, pageSize, sortOrder, startDate, navigate]);

  const handleClearFilters = () => {
    setSortOrder("");
    setSearchTerm("");
    setStartDate(new Date());
    setPageSize(null);
    setPage(null);
  };

  return (
    <div className="flex flex-col gap-4 p-4 bg-white rounded-xl w-full">
      <div className="flex sm:flex-row flex-col gap-4 items-center">
        <select
          value={pageSize || 10}
          onChange={(e) => setPageSize(Number(e.target.value))}
          className="border border-gray-400 text-slate-800 rounded-xl py-2 px-3 focus:outline-none focus:ring-2 focus:ring-cyan-600"
        >
          <option value={5}>5 / page</option>
          <option value={10}>10 / page</option>
          <option value={20}>20 / page</option>
          <option value={50}>50 / page</option>
        </select>
        <Tooltip title="Starts from" placement="top">
          <div>
            <MyDatePicker
              value={startDate}
              onChange={setStartDate}
              placeholder="Select end date "
              disabled={!startDate}
              minDate={new Date()}
              setDate={setStartDate}
              theme={
                "bg-white focus:outline-hidden focus:ring-2 focus:ring-cyan-600 text-black"
              }
            ></MyDatePicker>
          </div>
        </Tooltip>

        <Tooltip title="Sort by input date" placement="top">
          <button
            variant="contained"
            onClick={() =>
              setSortOrder((prev) => (prev === "asc" ? "desc" : "asc"))
            }
            color="primary"
            className="flex items-center gap-2 bg-white btn btn-primary rounded-xl w-30 text-black border-gray-400 shadow-none border focus:outline-hidden focus:ring-2 focus:ring-cyan-600"
          >
            {sortOrder === "asc" ? (
              <div className="flex gap-4">
                <p>Newest</p>
                <FiArrowUp size={20} />
              </div>
            ) : (
              <div className="flex gap-4">
                <p>Oldest</p>
                <FiArrowDown size={20} />
              </div>
            )}
          </button>
        </Tooltip>
      </div>
      <div className="flex justify-between">
        <div className="relative flex items-center w-[40%]">
          <input
            type="text"
            placeholder="Search Products"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="border border-gray-400 text-slate-800 rounded-xl py-2 pl-10 pr-4 w-full focus:outline-hidden focus:ring-2 focus:ring-cyan-600"
          />
          <FiSearch className="absolute left-3 text-slate-800 size={20}" />
        </div>
        <div className="self-end flex gap-5">
          <button
            className="flex items-center gap-2 bg-rose-900 text-white px-3 py-2 rounded-xl transition duration-300 ease-in btn btn-primary border-none w-30 shadow-none"
            onClick={handleClearFilters}
          >
            <FiRefreshCw className="font-semibold" size={16} />
            <span className="font-semibold">Clear Filter</span>
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProjectFilter;
