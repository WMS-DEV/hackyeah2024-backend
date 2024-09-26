package pl.wmsdev.unisearch.config;


import org.springframework.core.convert.converter.Converter;

@org.springframework.data.convert.ReadingConverter
public class ReadingConverter implements Converter<Integer, Boolean> {

    @Override
    public Boolean convert(Integer source) {
        return source == 1;
    }

}
